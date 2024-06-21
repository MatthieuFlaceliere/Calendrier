package fr.esgi.calendrier.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErreurController implements ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Une erreur est survenue: " + ex.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
