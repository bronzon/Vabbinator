package se.olle.vabinator.domain.settings;


import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EmailSettings {
    private final SharedPreferences sharedPreferences;

    @Inject
    public EmailSettings(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getMailToAddress() {
        return sharedPreferences.getString("workEmail", null);
    }

    public String getReportVabText() {
        return sharedPreferences.getString("startEmailText", null);
    }

    public String getReportBackToWorkText() {
        return sharedPreferences.getString("endEmailText", null);
    }

    public String getSubjectText() {
        return sharedPreferences.getString("emailSubject", null);
    }

    @Override
    public String toString() {
        return getMailToAddress() + getReportBackToWorkText() + getReportVabText() + getSubjectText();
    }
}
