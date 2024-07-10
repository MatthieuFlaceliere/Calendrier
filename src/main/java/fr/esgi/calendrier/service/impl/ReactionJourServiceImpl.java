package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.TypeReaction;
import fr.esgi.calendrier.business.ReactionJour;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.business.customId.ReactionJourId;
import fr.esgi.calendrier.repository.ReactionJourRepository;
import fr.esgi.calendrier.service.ReactionJourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ReactionJourServiceImpl implements ReactionJourService {

    private final ReactionJourRepository reactionJourRepository;

    @Override
    public void addOrRemoveReactionJour(JourId jourId, TypeReaction typeReaction, Utilisateur utilisateur) {
        if (reactionJourRepository.existsById(new ReactionJourId(jourId, utilisateur.getId(), typeReaction.getId()))) {
            reactionJourRepository.deleteById(new ReactionJourId(jourId, utilisateur.getId(), typeReaction.getId()));
        } else {
            ReactionJour reactionJour = new ReactionJour();
            reactionJour.setId(new ReactionJourId(jourId, utilisateur.getId(), typeReaction.getId()));
            reactionJour.setUtilisateur(utilisateur);
            reactionJour.setTypeReaction(typeReaction);
            reactionJourRepository.save(reactionJour);
        }
    }
}
