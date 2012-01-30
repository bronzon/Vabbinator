package se.olle.vabinator.reporter.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.domain.settings.EmailSettings;
import se.olle.vabinator.reporter.BackToWorkReporter;
import se.olle.vabinator.reporter.VabReporter;

@Singleton
public class EmailWorkReporterImpl implements VabReporter, BackToWorkReporter {
    private final EmailSettings emailSettings;

    @Inject
    public EmailWorkReporterImpl(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    public void reportVAB(VabEvent event) {
        System.out.println("Sending email to report vab " + emailSettings);
    }

    @Override
    public void reportBackToWork() {
        System.out.println("Sending email report to work " + emailSettings);
    }
}
