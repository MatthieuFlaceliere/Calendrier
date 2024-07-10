package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.TypeReaction;
import fr.esgi.calendrier.repository.TypeReactionRepository;
import fr.esgi.calendrier.service.TypeReactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TypeReactionServiceImpl implements TypeReactionService {
    private final TypeReactionRepository typeReactionRepository;

    @Override
    public void save(TypeReaction typeReaction) {
        this.typeReactionRepository.save(typeReaction);
    }

    @Override
    public TypeReaction findById(Long id) {
        return this.typeReactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<TypeReaction> findAll() {
        return this.typeReactionRepository.findAll();
    }
}
