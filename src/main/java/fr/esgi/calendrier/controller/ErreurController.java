package fr.esgi.calendrier.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErreurController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            String errorMessageModel = "errorMessage";
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.addObject(errorMessageModel, "La page demandée n'existe pas");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.addObject(errorMessageModel, "Une erreur interne est survenue");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                modelAndView.addObject(errorMessageModel, "Vous n'avez pas le droit d'accéder à cette page");
            } else {
                modelAndView.addObject(errorMessageModel, "Une erreur est survenue");
            }

        }
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
