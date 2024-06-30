package fr.esgi.calendrier.controller;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.Reaction;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.dto.UtilisateurDto;
import fr.esgi.calendrier.mapper.UtilisateurMapper;
import fr.esgi.calendrier.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

    private final JourService jourService;
    private final GifService gifService;
    private final ReactionService reactionService;
    private final ReactionJourService reactionJourService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        model.addAttribute("utilisateur", utilisateurDto);
        return "register";
    }

    @PostMapping("/register")
    public String registration(UtilisateurDto utilisateurDto, Model model) {
        utilisateurService.save(utilisateurMapper.toEntity(utilisateurDto));
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(
            Model model,
            @PageableDefault(size = 7) Pageable pageable
    ) {
        model.addAttribute("jours", jourService.findAll(pageable));
        model.addAttribute("reactions", reactionService.findAll());

        return "index";
    }

    @GetMapping("gif/save/form/{jour}/{mois}")
    public String gifDistant(
            Model model,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        Jour jourEntity = jourService.findById(jourId);

        model.addAttribute("jour", jourEntity);

        return "gif-distant";
    }

    @PostMapping("gif/save/form/{jour}/{mois}")
    public String addGif(
            String url,
            String legende,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        Utilisateur utilisateur = utilisateurService.utilisateurFromSecurityContext(SecurityContextHolder.getContext());

        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        Jour jourEntity = jourService.findById(jourId);

        if (jourEntity.getGif() != null) {
            throw new RuntimeException("Gif déjà ajouté");
        }

        if (utilisateur.getSolde() < jourEntity.getPoints()) {
            throw new RuntimeException("Solde insuffisant");
        }

        // Création du gif
        Gif gif = new Gif();
        gif.setUrl(url);
        gif.setLegende(legende);
        gifService.save(gif);

        // Ajout du gif au jour
        jourService.setGif(jourId, gif, utilisateur);

        // Soustraction des points
        utilisateurService.subractPoints(utilisateur, jourEntity.getPoints());

        return "redirect:/";
    }

    @GetMapping("/jour/reaction/{jour}/{mois}/{reaction}")
    public String addReaction(
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois,
            @PathVariable(value = "reaction") String reaction
    ) {
        Utilisateur utilisateur = utilisateurService.utilisateurFromSecurityContext(SecurityContextHolder.getContext());

        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));

        Reaction reactionEntity = reactionService.findById(Long.parseLong(reaction));
        reactionJourService.addReactionJour(jourId, reactionEntity, utilisateur);

        return "redirect:/";
    }
}
