package fr.esgi.calendrier.service;

import fr.esgi.calendrier.business.Utilisateur;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UtilisateurService extends UserDetailsService {
    public void save(Utilisateur utilisateur);

    public Utilisateur findByEmail(String email);

    public Utilisateur subractPoints(Utilisateur utilisateur, int points);

    public Utilisateur utilisateurFromSecurityContext(SecurityContext securityContext);
}
