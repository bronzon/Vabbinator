package se.olle.vabinator.reporter.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.domain.settings.EmailSettings;
import se.olle.vabinator.reporter.BackToWorkReporter;
import se.olle.vabinator.reporter.VabReporter;

@Singleton
public class EmailWorkReporterImpl implements VabReporter, BackToWorkReporter {
    private final Context context;
    private final EmailSettings emailSettings;

    @Inject
    public EmailWorkReporterImpl(Context context, EmailSettings emailSettings) {
        this.context = context;
        this.emailSettings = emailSettings;
        Log.i("EmailWorkReporterImpl", emailSettings.toString());
    }

    public void reportVAB(VabEvent event) {
        sendMail(emailSettings.getMailToAddress(), emailSettings.getSubjectText(), emailSettings.getReportVabText());
    }

    private void sendMail(String toAddress, String subject, String text) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, toAddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(emailIntent, "Sending mail..."));
    }

    @Override
    public void reportBackToWork() {
        sendMail(emailSettings.getMailToAddress(), emailSettings.getSubjectText(), emailSettings.getReportBackToWorkText());
    }
}
