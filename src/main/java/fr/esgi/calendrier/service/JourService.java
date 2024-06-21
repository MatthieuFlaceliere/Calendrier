package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.customId.JourId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JourService {
    public void save(Jour jour);

    public void setGif(JourId id, Gif gif);

    public Page<Jour> findAll(Pageable pageable);

    public Jour findById(JourId id);
}
