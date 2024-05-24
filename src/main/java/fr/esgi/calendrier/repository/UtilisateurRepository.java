package fr.esgi.calendrier.repository;

import fr.esgi.calendrier.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String username);
}
