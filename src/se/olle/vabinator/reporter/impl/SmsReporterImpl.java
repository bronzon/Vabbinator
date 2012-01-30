package se.olle.vabinator.reporter.impl;

import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.reporter.VabReporter;

public class SmsReporterImpl implements VabReporter {
    public void reportVAB(VabEvent event) {
        System.out.println("sending the sms to " + event);
    }
}
