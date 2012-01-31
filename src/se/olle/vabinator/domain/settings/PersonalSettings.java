package se.olle.vabinator.domain.settings;

import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PersonalSettings {
    private SharedPreferences sharedPreferences;

    @Inject
    public PersonalSettings(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getChildPersonnummer() {
        return sharedPreferences.getString("childPnr", "childpnr");
    }

    public String getParentPersonnummer() {
        return sharedPreferences.getString("parentPnr", "parentpnr");
    }

    public String getChildName() {
        return sharedPreferences.getString("childName", "childname");
    }

    @Override
    public String toString() {
        return getChildName() + ":" + getChildPersonnummer() + ":" + getParentPersonnummer();
    }
}
