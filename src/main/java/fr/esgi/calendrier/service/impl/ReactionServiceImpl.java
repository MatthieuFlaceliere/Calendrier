package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Reaction;
import fr.esgi.calendrier.repository.ReactionRepository;
import fr.esgi.calendrier.service.ReactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ReactionRepository reactionRepository;

    @Override
    public void save(Reaction reaction) {
        this.reactionRepository.save(reaction);
    }

    @Override
    public Reaction findById(Long id) {
        return this.reactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reaction> findAll() {
        return this.reactionRepository.findAll();
    }
}
