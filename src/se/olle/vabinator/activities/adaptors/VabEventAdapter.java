package se.olle.vabinator.activities.adaptors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import se.olle.vabinator.R;
import se.olle.vabinator.activities.CurrentActivity;
import se.olle.vabinator.activities.IntentExtrasKeys;
import se.olle.vabinator.activities.MainTabActivity;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.utils.EventLabelTextGenerator;

import java.util.List;

public class VabEventAdapter extends ArrayAdapter<VabEvent> {
    private final Activity activity;
    private final List<VabEvent> list;
    private final VabEventDao vabEventDao;
    private final EventLabelTextGenerator eventLabelTextGenerator;
    private final MainTabActivity.Tabs tabToGoToAfterDelete;

    public VabEventAdapter(Activity activity, List<VabEvent> list, VabEventDao vabEventDao, EventLabelTextGenerator eventLabelTextGenerator, MainTabActivity.Tabs tabToGoToAfterDelete) {
        super(activity, R.layout.vabeventrow, list);
        this.activity = activity;
        this.list = list;
        this.vabEventDao = vabEventDao;
        this.eventLabelTextGenerator = eventLabelTextGenerator;
        this.tabToGoToAfterDelete = tabToGoToAfterDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final VabEvent vabEvent = list.get(position);
        LayoutInflater inflator = activity.getLayoutInflater();
        View view = inflator.inflate(R.layout.vabeventrow, null);
        TextView label = (TextView) view.findViewById(R.id.rowLabel);
        label.setText(eventLabelTextGenerator.getText(vabEvent));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CurrentActivity.class);
                intent.putExtra(IntentExtrasKeys.CURRENT_VAB_EVENT_ID, vabEvent.getId());
                activity.startActivity(intent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(activity)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.removeConfirmation)
                        .setMessage(R.string.removeConfirmation)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                vabEventDao.delete(vabEvent);
                                Intent intent = new Intent(activity, MainTabActivity.class);
                                intent.putExtra(IntentExtrasKeys.TAB_TO_GO_TO, tabToGoToAfterDelete.getTabIndex());
                                activity.startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
                return true;
            }
        });

        return view;
    }

}
