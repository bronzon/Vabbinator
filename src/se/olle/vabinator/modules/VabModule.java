package se.olle.vabinator.modules;

import android.content.Context;
import android.content.res.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import se.olle.vabinator.R;
import se.olle.vabinator.domain.settings.EmailSettings;
import se.olle.vabinator.domain.settings.PersonalSettings;
import se.olle.vabinator.persistance.VabEventDao;
import se.olle.vabinator.persistance.VabEventDaoXmlSimpleImpl;
import se.olle.vabinator.reporter.BackToWorkReporter;
import se.olle.vabinator.reporter.VabReporter;
import se.olle.vabinator.reporter.impl.EmailWorkReporterImpl;
import se.olle.vabinator.reporter.impl.ReporterServiceImpl;
import se.olle.vabinator.reporter.impl.SmsReporterImpl;
import se.olle.vabinator.service.IDGenerator;
import se.olle.vabinator.service.IDGeneratorImpl;
import se.olle.vabinator.service.VabService;
import se.olle.vabinator.service.VabServiceImpl;
import se.olle.vabinator.utils.EventLabelTextGenerator;
import se.olle.vabinator.utils.EventLabelTextGeneratorImpl;

import java.util.ArrayList;
import java.util.List;

public class VabModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VabService.class).to(VabServiceImpl.class).in(Scopes.SINGLETON);
        bind(VabEventDao.class).to(VabEventDaoXmlSimpleImpl.class).in(Scopes.SINGLETON);
        bind(IDGenerator.class).to(IDGeneratorImpl.class).in(Scopes.SINGLETON);
        bind(VabReporter.class).annotatedWith(EmailReporter.class).to(EmailWorkReporterImpl.class).in(Scopes.SINGLETON);
        bind(BackToWorkReporter.class).to(EmailWorkReporterImpl.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    VabReporter getVabReporter(EmailSettings emailSettings) {
        List<VabReporter> reporters = new ArrayList<VabReporter>();
        reporters.add(new EmailWorkReporterImpl(emailSettings));
        reporters.add(new SmsReporterImpl());
        VabReporter reporter = new ReporterServiceImpl(reporters);
        return reporter;
    }

    @Provides
    @Singleton
    PersonalSettings getCurrentPersonalSettings() {
        return PersonalSettings.DEFAULT_PERSONAL_SETTINGS;
    }

    @Provides
    @Singleton
    EmailSettings getCurrentEmailSettings() {
        return EmailSettings.DEFAULT_EMAIL_SETTINGS;
    }

    @Provides
    @Singleton
    EventLabelTextGenerator getEventLabelTextGenerator(Context context) {
        Resources resources = context.getResources();
        return new EventLabelTextGeneratorImpl(resources.getString(R.string.reported), resources.getString(R.string.for_child));
    }


}
