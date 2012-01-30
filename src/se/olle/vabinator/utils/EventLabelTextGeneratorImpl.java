package se.olle.vabinator.utils;

import com.google.inject.Singleton;
import se.olle.vabinator.domain.VabEvent;

import java.text.SimpleDateFormat;

@Singleton
public class EventLabelTextGeneratorImpl implements EventLabelTextGenerator {

    private final String reported;
    private final String forChild;

    public EventLabelTextGeneratorImpl(String reported, String forChild) {
        this.reported = reported;
        this.forChild = forChild;
    }

    @Override
    public String getText(VabEvent vabEvent) {
        String eventDateAsString = new SimpleDateFormat("EEE, d MMM yy").format(vabEvent.getDateOfReport());
        StringBuilder sb = new StringBuilder(100).append(reported).append(' ').append(eventDateAsString);
        sb.append("\n");
        sb.append(forChild).append(' ').append(vabEvent.getChild().getName());
        return sb.toString();
    }
}
