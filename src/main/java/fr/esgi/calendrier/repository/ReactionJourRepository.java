package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.ReactionJour;
import fr.esgi.calendrier.business.customId.ReactionJourId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionJourRepository extends JpaRepository<ReactionJour, ReactionJourId> {
}
