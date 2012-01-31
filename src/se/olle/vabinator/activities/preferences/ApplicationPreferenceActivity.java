package se.olle.vabinator.activities.preferences;

import android.os.Bundle;
import roboguice.activity.RoboPreferenceActivity;
import se.olle.vabinator.R;

public class ApplicationPreferenceActivity extends RoboPreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
