package se.olle.vabinator.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import se.olle.vabinator.R;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.modules.EmailReporter;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.reporter.BackToWorkReporter;
import se.olle.vabinator.reporter.VabReporter;
import se.olle.vabinator.utils.EventLabelTextGenerator;

import java.util.Date;

public class CurrentActivity extends RoboActivity {
    @InjectView(R.id.currentInfoLabel)
    private TextView infoLabel;
    @InjectView(R.id.currentAddDate)
    private ImageButton addDateButton;
    @InjectView(R.id.currentCloseEvent)
    private ToggleButton closeEventButton;
    @InjectView(R.id.currentSentTestimonial)
    private ToggleButton testimonialButton;
    @InjectView(R.id.currentReportBackToWork)
    private ImageButton reportBackToWorkButton;
    @InjectView(R.id.currentShowCalendar)
    private ImageButton showCalendarButton;

    @Inject
    private Context context;
    @Inject
    private VabEventDao vabEventDao;
    @Inject
    private BackToWorkReporter backToWorkReporter;
    @Inject
    private EventLabelTextGenerator eventLabelTextGenerator;
    @Inject
    @EmailReporter
    private VabReporter emailWorkReporter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentactivity);
        VabEvent vabEvent = getVabEvent();
        if (vabEvent != null) {
            setupLabel(vabEvent);
            setupAddDateButton(vabEvent);
            setupCloseEventButton(vabEvent);
            setupTestimonialButton(vabEvent);
            setupReportBackToWorkButton();
            setupShowCalendarButton(vabEvent);
        }

    }

    private void setupShowCalendarButton(final VabEvent vabEvent) {
        showCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentActivity.this, CalendarActivity.class);
                intent.putExtra(IntentExtrasKeys.CURRENT_VAB_EVENT_ID, vabEvent.getId());
                startActivity(intent);
            }
        });
    }

    private void setupReportBackToWorkButton() {

    }

    private void setupTestimonialButton(final VabEvent vabEvent) {
        testimonialButton.setChecked(vabEvent.isSentTestimonial());
        testimonialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vabEvent.setSentTestimonial(!vabEvent.isSentTestimonial());
                vabEventDao.save(vabEvent);
            }
        });
    }

    private void setupCloseEventButton(final VabEvent vabEvent) {
        closeEventButton.setChecked(vabEvent.isClosed());
        closeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vabEvent.setClosed(!vabEvent.isClosed());
                vabEventDao.save(vabEvent);
            }
        });
    }

    private VabEvent getVabEvent() {
        Bundle extras = getIntent().getExtras();
        Long eventId = (Long) extras.get(IntentExtrasKeys.CURRENT_VAB_EVENT_ID);
        return vabEventDao.findById(eventId);
    }

    private void setupAddDateButton(final VabEvent vabEvent) {
        addDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vabEvent.addDate(new Date());
                vabEventDao.save(vabEvent);
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.reMail)
                        .setMessage(R.string.reMail)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                emailWorkReporter.reportVAB(vabEvent);
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();

            }
        });
    }

    private void setupLabel(VabEvent vabEvent) {
        infoLabel.setText(eventLabelTextGenerator.getText(vabEvent));
    }
}
