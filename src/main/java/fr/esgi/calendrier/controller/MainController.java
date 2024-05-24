package fr.esgi.calendrier.controller;

import fr.esgi.calendrier.dto.UtilisateurDto;
import fr.esgi.calendrier.mapper.UtilisateurMapper;
import fr.esgi.calendrier.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;

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
    public String home() {
        return "index";
    }
}
