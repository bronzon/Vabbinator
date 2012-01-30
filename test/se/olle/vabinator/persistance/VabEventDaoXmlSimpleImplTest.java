package se.olle.vabinator.persistance;

import android.content.Context;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import se.mockachino.annotations.Mock;
import se.olle.vabinator.domain.Person;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.service.IDGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static se.mockachino.Mockachino.setupMocks;
import static se.mockachino.Mockachino.when;
import static se.mockachino.matchers.Matchers.any;

public class VabEventDaoXmlSimpleImplTest {
    public static final String FILE_NAME = "VabEvent123.xml";
    @Mock
    private Context context;
    @Mock
    private IDGenerator idGenerator;
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() {
        setupMocks(this);
    }


    @Test
    public void testRoundTripMarshallAndUnmarshall() throws FileNotFoundException {
        File temporaryFolderRoot = temporaryFolder.getRoot();

        VabEventDaoXmlSimpleImpl vabEventDaoXmlSimple = new VabEventDaoXmlSimpleImpl(context, idGenerator);

        when(idGenerator.generateRandomLongId()).thenReturn(123l);
        when(context.openFileOutput(any(String.class), any(Integer.class))).thenReturn(new FileOutputStream(new File(temporaryFolderRoot, FILE_NAME)));
        VabEvent vabEvent = new VabEvent(new Person("asdasd", "asdasd"), new Person("asd", "asd"), new Date());
        vabEventDaoXmlSimple.save(vabEvent);

        when(context.getFilesDir()).thenReturn(temporaryFolderRoot);
        when(context.openFileInput(FILE_NAME)).thenReturn(new FileInputStream(new File(temporaryFolderRoot, FILE_NAME)));
        List<VabEvent> vabEvents = vabEventDaoXmlSimple.findAll();
        assertEquals(1, vabEvents.size());
        VabEvent event = vabEvents.get(0);
        assertEquals(event, vabEvent);
        assertEquals(123l, event.getId());
    }

    @Test
    public void testFindById() throws FileNotFoundException {
        File temporaryFolderRoot = temporaryFolder.getRoot();
        VabEventDaoXmlSimpleImpl vabEventDaoXmlSimple = new VabEventDaoXmlSimpleImpl(context, idGenerator);
        when(idGenerator.generateRandomLongId()).thenReturn(123l);
        when(context.openFileOutput(any(String.class), any(Integer.class))).thenReturn(new FileOutputStream(new File(temporaryFolderRoot, FILE_NAME)));
        VabEvent vabEvent = new VabEvent(new Person("asdasd", "asdasd"), new Person("asd", "asd"), new Date());
        vabEventDaoXmlSimple.save(vabEvent);

        when(context.openFileInput(FILE_NAME)).thenReturn(new FileInputStream(new File(temporaryFolderRoot, FILE_NAME)));

        VabEvent locatedEvent = vabEventDaoXmlSimple.findById(123l);
        assertEquals(vabEvent, locatedEvent);

    }
}
