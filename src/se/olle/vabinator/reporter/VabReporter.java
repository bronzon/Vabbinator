package se.olle.vabinator.reporter;

import se.olle.vabinator.domain.VabEvent;

public interface VabReporter {
    void reportVAB(VabEvent event);
}
