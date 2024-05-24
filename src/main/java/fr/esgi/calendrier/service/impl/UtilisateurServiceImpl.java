package fr.esgi.calendrier.service.impl;

import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.repository.UtilisateurRepository;
import fr.esgi.calendrier.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        return utilisateur;
    }

    @Override
    public void save(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setSolde(500);

        this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return this.utilisateurRepository.findByEmail(email);
    }
}
