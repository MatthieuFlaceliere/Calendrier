package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.repository.GifRepository;
import fr.esgi.calendrier.service.GifService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class GifServiceImpl implements GifService {
    private final GifRepository gifRepository;

    @Override
    public void save(Gif gif) {
        this.gifRepository.save(gif);
    }

    @Override
    public Gif findById(Long id) {
        return this.gifRepository.findById(id).orElse(null);
    }
}
