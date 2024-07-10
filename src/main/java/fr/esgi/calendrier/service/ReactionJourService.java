package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.TypeReaction;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;

public interface ReactionJourService {
    void addOrRemoveReactionJour(JourId jourId, TypeReaction typeReaction, Utilisateur utilisateur);
}
