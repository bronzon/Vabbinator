package se.olle.vabinator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import com.google.inject.Inject;
import roboguice.activity.RoboTabActivity;
import se.olle.vabinator.R;
import se.olle.vabinator.utils.OptionsMenuHandler;

public class MainTabActivity extends RoboTabActivity {
    @Inject
    private OptionsMenuHandler optionsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, ReportActivity.class);
        spec = tabHost.newTabSpec("Report").setIndicator("Report",
                getResources().getDrawable(R.drawable.ic_tab_report))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, HistoryActivity.class);
        spec = tabHost.newTabSpec("history").setIndicator("History",
                getResources().getDrawable(R.drawable.ic_tab_history))
                .setContent(intent);
        tabHost.addTab(spec);


        tabHost.setCurrentTab(0);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer tabId = (Integer) extras.get(IntentExtrasKeys.TAB_TO_GO_TO);
            if (tabId != null) {
                tabHost.setCurrentTab(tabId);
            }
        }
    }

    public static enum Tabs {
        REPORT(0),
        HISTORY(1);
        private final int tabIndex;

        Tabs(int tabIndex) {
            this.tabIndex = tabIndex;
        }

        public int getTabIndex() {
            return tabIndex;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return optionsMenuHandler.onCreateOptionsMenu(getMenuInflater(), menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return optionsMenuHandler.onOptionsItemSelected(this, item);
    }
}
