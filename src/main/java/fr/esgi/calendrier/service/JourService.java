package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Jour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JourService {
    public void save(Jour jour);

    public Page<Jour> findAll(Pageable pageable);
}
