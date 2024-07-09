package fr.esgi.calendrier.controller;

import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RequiredArgsConstructor
public class ErreurController {

    private final UtilisateurService utilisateurService;

    Logger logger = LoggerFactory.getLogger(ErreurController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        logger.warn("Une erreur est survenue: {}", ex.getMessage());
        modelAndView.addObject("errorMessage", "Une erreur est survenue: " + ex.getMessage());
        modelAndView.setViewName("error");

        try {
            Utilisateur currentUser = utilisateurService.utilisateurFromSecurityContext(SecurityContextHolder.getContext());
            modelAndView.addObject("theme", currentUser.getTheme());
        } catch (Exception e) {
            modelAndView.addObject("theme", "light");
        }

        return modelAndView;
    }
}
