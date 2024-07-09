package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UtilisateurService extends UserDetailsService {
    Page<Utilisateur> findAll(Pageable pageable);

    Utilisateur findByEmail(String email);

    Utilisateur findById(Long id);

    void save(Utilisateur utilisateur);

    void delete(Utilisateur utilisateur);

    Utilisateur subractPoints(Utilisateur utilisateur, int points);

    Utilisateur utilisateurFromSecurityContext(SecurityContext securityContext);
}
