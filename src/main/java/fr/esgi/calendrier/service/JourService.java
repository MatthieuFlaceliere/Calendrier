package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JourService {
    void save(Jour jour);

    void setGif(JourId id, Gif gif, Utilisateur utilisateur);

    Page<Jour> findAll(Pageable pageable);

    Jour findById(JourId id);

    void delete(Jour jour);
}
