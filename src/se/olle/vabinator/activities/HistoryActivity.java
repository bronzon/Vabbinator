package se.olle.vabinator.activities;

import android.os.Bundle;
import android.widget.ListView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import se.olle.vabinator.R;
import se.olle.vabinator.activities.adaptors.VabEventAdapter;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.utils.EventLabelTextGenerator;

import java.util.List;

public class HistoryActivity extends RoboActivity {
    @Inject
    private VabEventDao vabEventDao;
    @Inject
    private EventLabelTextGenerator eventLabelTextGenerator;
    @InjectView(R.id.inActiveList)
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyactivity);
        updateListOfActive();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListOfActive();
    }


    private void updateListOfActive() {
        List<VabEvent> vabEvents = vabEventDao.findAllInactive();
        listView.setAdapter(new VabEventAdapter(this, vabEvents, vabEventDao, eventLabelTextGenerator, MainTabActivity.Tabs.HISTORY));
    }
}
