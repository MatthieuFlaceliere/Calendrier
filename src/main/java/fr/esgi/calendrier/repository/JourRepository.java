package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.customId.JourId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourRepository extends JpaRepository<Jour, JourId> {
}
