package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.repository.JourRepository;
import fr.esgi.calendrier.service.JourService;
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
    public Page<Jour> findAll(Pageable pageable) {
        return this.jourRepository.findAll(pageable);
    }
}
