package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.Gif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GifRepository extends JpaRepository<Gif, Long> {
}
