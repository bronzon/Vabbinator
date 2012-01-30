package se.olle.vabinator.persistance;

import se.olle.vabinator.domain.VabEvent;

import java.util.List;

public interface VabEventDao {
    public List<VabEvent> findAll();

    public void delete(VabEvent vabEvent);

    public void save(VabEvent vabEvent);

    public VabEvent findById(Long event_id);

    public List<VabEvent> findAllActive();

    public List<VabEvent> findAllInactive();
}
