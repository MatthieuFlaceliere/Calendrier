package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Reaction;

public interface ReactionService {
    public void save(Reaction reaction);

    public Reaction findById(Long id);
}
