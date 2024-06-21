package fr.esgi.calendrier.controller;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.dto.UtilisateurDto;
import fr.esgi.calendrier.mapper.UtilisateurMapper;
import fr.esgi.calendrier.service.GifService;
import fr.esgi.calendrier.service.JourService;
import fr.esgi.calendrier.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        return "index";
    }

    @GetMapping("gif/save/form/{jour}/{mois}")
    public String gifDistant(
            Model model,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));

        model.addAttribute("jour", jourService.findById(jourId));

        return "gif-distant";
    }

    @PostMapping("gif/save/form/{jour}/{mois}")
    public String addGif(
            String url,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        Gif gif = new Gif();
        gif.setUrl(url);
        gifService.save(gif);

        JourId jourId = new JourId(Integer.parseInt(jour), Integer.parseInt(mois));
        jourService.setGif(jourId, gif);

        return "redirect:/";
    }
}
