package se.olle.vabinator.utils;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public interface OptionsMenuHandler {
    public boolean onOptionsItemSelected(Activity previousActivity, MenuItem item);

    public boolean onCreateOptionsMenu(MenuInflater inflater, Menu menu);
}
