package se.olle.vabinator.reporter.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.reporter.VabReporter;

import java.util.List;

@Singleton
public class ReporterServiceImpl implements VabReporter {
    private final List<VabReporter> vabReporters;

    @Inject
    public ReporterServiceImpl(List<VabReporter> vabReporters) {
        this.vabReporters = vabReporters;
    }

    @Override
    public void reportVAB(VabEvent event) {
        for (VabReporter vabReporter : vabReporters) {
            vabReporter.reportVAB(event);
        }
    }

}
