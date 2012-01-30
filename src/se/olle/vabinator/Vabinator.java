package se.olle.vabinator;

import com.google.inject.Module;
import roboguice.application.RoboApplication;
import se.olle.vabinator.modules.VabModule;

import java.util.List;

public class Vabinator extends RoboApplication {
    @Override
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new VabModule());
    }
}
