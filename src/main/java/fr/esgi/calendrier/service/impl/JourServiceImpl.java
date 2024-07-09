package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.repository.JourRepository;
import fr.esgi.calendrier.service.JourService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class JourServiceImpl implements JourService {
    private final JourRepository jourRepository;

    @Override
    public void save(Jour jour) {
        this.jourRepository.save(jour);
    }

    @Override
    public void setGif(JourId id, Gif gif, Utilisateur utilisateur) {
        Jour jour = this.jourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jour not found"));
        jour.setGif(gif);
        jour.setUtilisateur(utilisateur);
        this.jourRepository.save(jour);
    }

    @Override
    public Page<Jour> findAll(Pageable pageable) {
        return this.jourRepository.findAll(pageable);
    }

    @Override
    public Jour findById(JourId id) {
        return this.jourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jour not found"));
    }

    @Override
    public void delete(Jour jour) {
        this.jourRepository.delete(jour);
    }
}
