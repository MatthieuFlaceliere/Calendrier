package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.TypeReaction;

import java.util.List;

public interface TypeReactionService {
    void save(TypeReaction typeReaction);

    TypeReaction findById(Long id);

    List<TypeReaction> findAll();
}
