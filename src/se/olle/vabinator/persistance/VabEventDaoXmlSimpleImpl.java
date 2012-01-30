package se.olle.vabinator.persistance;

import android.content.Context;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.service.IDGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Singleton
public class VabEventDaoXmlSimpleImpl implements VabEventDao {
    private final Serializer serializer;
    private final Context context;
    private final IDGenerator idGenerator;
    private Pattern pattern = Pattern.compile("VabEvent.*\\.xml");
    private static final String FILE_PREFIX = "VabEvent";

    @Inject
    public VabEventDaoXmlSimpleImpl(Context context, IDGenerator idGenerator) {
        this.context = context;
        this.idGenerator = idGenerator;
        this.serializer = new Persister();
    }

    @Override
    public List<VabEvent> findAll() {
        List<VabEvent> vabEvents = new ArrayList<VabEvent>();
        File[] eventFiles = context.getFilesDir().listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                return pattern.matcher(s).matches();
            }
        });
        for (File eventFile : eventFiles) {
            try {
                String name = eventFile.getName();
                FileInputStream fileInputStream = context.openFileInput(name);
                VabEvent event = serializer.read(VabEvent.class, fileInputStream);
                vabEvents.add(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return vabEvents;
    }

    @Override
    public void save(VabEvent vabEvent) {
        if (vabEvent.getId() == 0) {
            vabEvent.setId(idGenerator.generateRandomLongId());
        }
        try {
            FileOutputStream fos = context.openFileOutput(getFileName(vabEvent.getId()), Context.MODE_WORLD_READABLE);
            try {
                serializer.write(vabEvent, fos);
            } catch (Exception e) {
                throw new RuntimeException("could not marshall xml", e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public VabEvent findById(Long eventId) {
        try {
            String fileName = getFileName(eventId);
            if (fileName == null) {
                return null;
            }
            FileInputStream fileInputStream = context.openFileInput(fileName);
            return serializer.read(VabEvent.class, fileInputStream);
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Could not unmarshall xml", e);
        }
    }

    @Override
    public List<VabEvent> findAllActive() {
        List<VabEvent> all = findAll();
        List<VabEvent> active = new ArrayList<VabEvent>();
        for (VabEvent vabEvent : all) {
            if (!vabEvent.isClosed()) {
                active.add(vabEvent);
            }
        }
        return active;
    }

    @Override
    public List<VabEvent> findAllInactive() {
        List<VabEvent> all = findAll();
        List<VabEvent> inactive = new ArrayList<VabEvent>();
        for (VabEvent vabEvent : all) {
            if (vabEvent.isClosed()) {
                inactive.add(vabEvent);
            }
        }
        return inactive;
    }

    private String getFileName(Long eventId) {
        if (eventId == null) {
            return null;
        }
        return FILE_PREFIX + eventId + ".xml";
    }

    @Override
    public void delete(VabEvent vabEvent) {
        if (vabEvent.getId() != 0) {
            context.deleteFile(getFileName(vabEvent.getId()));
        }
    }
}
