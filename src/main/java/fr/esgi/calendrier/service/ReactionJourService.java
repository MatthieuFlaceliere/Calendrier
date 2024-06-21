package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Reaction;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;

public interface ReactionJourService {
    public void addReactionJour(JourId jourId, Reaction reaction, Utilisateur utilisateur);
}
