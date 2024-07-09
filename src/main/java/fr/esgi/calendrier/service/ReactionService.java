package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Reaction;

import java.util.List;

public interface ReactionService {
    void save(Reaction reaction);

    Reaction findById(Long id);

    List<Reaction> findAll();
}
