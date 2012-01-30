package se.olle.vabinator.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import se.olle.vabinator.R;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.persistance.VabEventDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends RoboActivity {

    @Inject
    private VabEventDao vabEventDao;
    @InjectView(R.id.datesList)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendaractivity);
        VabEvent vabEvent = getVabEvent();

        if (vabEvent != null) {
            List<Date> dates = vabEvent.getDates();
            List<String> datesAsFormattedStrings = new ArrayList<String>();
            for (Date date : dates) {
                datesAsFormattedStrings.add(new SimpleDateFormat("EEE, d MMM yy").format(date));
            }

            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datesAsFormattedStrings));
        }
    }

    private VabEvent getVabEvent() {
        Bundle extras = getIntent().getExtras();
        Long eventId = (Long) extras.get(IntentExtrasKeys.CURRENT_VAB_EVENT_ID);
        return vabEventDao.findById(eventId);
    }
}
