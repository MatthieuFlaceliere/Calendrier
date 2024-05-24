package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.Jour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourRepository extends JpaRepository<Jour, Long> {
}
