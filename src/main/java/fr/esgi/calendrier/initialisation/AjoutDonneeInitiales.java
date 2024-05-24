package fr.esgi.calendrier.initialisation;

import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.business.JourId;
import fr.esgi.calendrier.service.JourService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class AjoutDonneeInitiales implements CommandLineRunner {

    private final JourService jourService;

    @Override
    public void run(String... args) throws Exception {
        ajoutDesJours();
    }

    private void ajoutDesJours() {
        int mois = new Date().getMonth();

        int nombreDeJours = 31;
        if (mois == 1) {
            // Février
            nombreDeJours = 28;
        } else if (mois == 3 || mois == 5 || mois == 8 || mois == 10) {
            // Avril, Juin, Septembre, Novembre
            nombreDeJours = 30;
        }


        for (int i = 1; i <= 31; i++) {
            JourId jourId = new JourId(i, mois);
            Jour jour = new Jour(jourId);
            jourService.save(jour);
        }
    }
}
