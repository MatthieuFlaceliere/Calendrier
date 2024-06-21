package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
}
