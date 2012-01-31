package se.olle.vabinator.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.inject.Singleton;
import se.olle.vabinator.R;
import se.olle.vabinator.activities.preferences.ApplicationPreferenceActivity;

@Singleton
public class OptionsMenuHandlerImpl implements OptionsMenuHandler {

    @Override
    public boolean onOptionsItemSelected(Activity previousActivity, MenuItem item) {
        Intent intent = new Intent(previousActivity, ApplicationPreferenceActivity.class);
        previousActivity.startActivity(intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.options, menu);
        return true;
    }
}
