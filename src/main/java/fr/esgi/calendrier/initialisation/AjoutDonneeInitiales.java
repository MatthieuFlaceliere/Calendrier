package fr.esgi.calendrier.initialisation;

import fr.esgi.calendrier.business.*;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Component
@AllArgsConstructor
@Profile("!test")
public class AjoutDonneeInitiales implements CommandLineRunner {

    private final JourService jourService;
    private final TypeReactionService typeReactionService;
    private final GifService gifService;
    private final UtilisateurService utilisateurService;

    private final Random random = new Random();

    @Override
    public void run(String... args) {
        ajoutDesReactions();
        ajoutDesJours();
        ajoutUtilisateurParDefaut();
        deleteAllUploadingGif();
    }

    private int getMonthDays(int month) {
        if (month == 1) {
            // Février
            return 28;
        } else if (month == 3 || month == 5 || month == 8 || month == 10) {
            // Avril, Juin, Septembre, Novembre
            return 30;
        } else {
            // Janvier, Mars, Mai, Juillet, Août, Octobre, Décembre
            return 31;
        }
    }

    private void ajoutDesJours() {
        int mois = new Date().getMonth();

        for (int i = 1; i <= getMonthDays(mois); i++) {
            JourId jourId = new JourId(i, mois);
            int point = random.nextInt(100);
            Jour jour = new Jour(jourId, point);
            jourService.save(jour);
        }
    }

    private void ajoutDesReactions() {
        ArrayList<String> emojis = new ArrayList<>();
        emojis.add("\uD83D\uDE00"); // 😀
        emojis.add("\uD83D\uDE02"); // 😂
        emojis.add("\uD83D\uDE0D"); // 😍
        emojis.add("\uD83D\uDE21"); // 😡

        for (String emoji : emojis) {
            TypeReaction typeReaction = new TypeReaction();
            typeReaction.setUnicode(emoji);
            typeReactionService.save(typeReaction);
        }
    }

    private void ajoutUtilisateurParDefaut() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("demo");
        utilisateur.setPrenom("demo");
        utilisateur.setPassword("demo1234");
        utilisateur.setEmail("demo@esgi.fr");
        utilisateur.setTheme("light");
        utilisateurService.save(utilisateur);
    }

    private void deleteAllUploadingGif() {
        try {
            gifService.deleteAllUploadingGif();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
