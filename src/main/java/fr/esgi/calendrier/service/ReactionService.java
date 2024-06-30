package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Reaction;

import java.util.List;

public interface ReactionService {
    public void save(Reaction reaction);

    public Reaction findById(Long id);

    public List<Reaction> findAll();
}
