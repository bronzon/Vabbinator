package se.olle.vabinator.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import se.olle.vabinator.domain.Child;
import se.olle.vabinator.domain.VabEvent;
import se.olle.vabinator.domain.settings.PersonalSettings;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.reporter.VabReporter;

import java.util.Date;

@Singleton
public class VabServiceImpl implements VabService {
    private final VabReporter reporter;
    private final PersonalSettings personalSettings;
    private final VabEventDao vabEventDao;

    @Inject
    public VabServiceImpl(VabReporter reporter, PersonalSettings personalSettings, VabEventDao vabEventDao) {
        this.reporter = reporter;
        this.personalSettings = personalSettings;
        this.vabEventDao = vabEventDao;
    }

    @Override
    public void doVab() {
        VabEvent event = new VabEvent(new Child(personalSettings.getChildPersonnummer(), personalSettings.getChildName()), new Date());
        vabEventDao.save(event);
        reporter.reportVAB(event);
        event.setReported(true);
    }
}
