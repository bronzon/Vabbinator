package se.olle.vabinator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import se.olle.vabinator.R;
import se.olle.vabinator.activities.adaptors.VabEventAdapter;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.reporter.VabReporter;
import se.olle.vabinator.service.VabService;
import se.olle.vabinator.utils.EventLabelTextGenerator;

import java.util.List;

public class ReportActivity extends RoboActivity {
    @InjectResource(R.string.app_name)
    private String myName;
    @InjectView(R.id.reportButton)
    private Button reportButton;
    @InjectView(R.id.activeList)
    private ListView listView;
    @Inject
    private VabService vabService;
    @Inject
    private VabEventDao vabEventDao;
    @Inject
    private VabReporter vabReporter;
    @Inject
    private EventLabelTextGenerator eventLabelTextGenerator;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportactivity);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vabService.doVab();
                updateListOfActive();
            }

        });

        updateListOfActive();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListOfActive();
    }


    private void updateListOfActive() {
        List<VabEvent> vabEvents = vabEventDao.findAllActive();
        listView.setAdapter(new VabEventAdapter(this, vabEvents, vabEventDao, eventLabelTextGenerator, MainTabActivity.Tabs.REPORT));
    }
}
