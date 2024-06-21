package fr.esgi.calendrier.initialisation;

import fr.esgi.calendrier.business.*;
import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.business.customId.ReactionJourId;
import fr.esgi.calendrier.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class AjoutDonneeInitiales implements CommandLineRunner {

    private final JourService jourService;
    private final ReactionService reactionService;
    private final GifService gifService;
    private final UtilisateurService utilisateurService;
    private final ReactionJourService reactionJourService;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        ajoutGif();
        ajoutDesReactions();
        ajoutDesJours();
        ajoutUtilisateurParDefaut();

        Utilisateur utilisateur = utilisateurService.findByEmail("demo@demo.com");
        Gif gif = gifService.findById(1L);

        JourId jourId = new JourId(1, 1);
        Jour jour = new Jour();
        jour.setId(jourId);
        jour.setGif(gif);
        jour.setUtilisateur(utilisateur);
        jour.setPoints(100);
        jourService.save(jour);

        Reaction reaction = reactionService.findById(1L);
        reactionJourService.addReactionJour(jourId, reaction, utilisateur);
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
            int point = random.nextInt(100);
            Jour jour = new Jour(jourId, null, null, null, null, point);
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
            Reaction reaction = new Reaction();
            reaction.setUnicode(emoji);
            reactionService.save(reaction);
        }
    }

    private void ajoutGif() {
        Gif gif = new Gif();
        gif.setUrl("https://c.tenor.com/sesbpnZ42swAAAAC/tenor.gif");
        gifService.save(gif);
    }

    private void ajoutUtilisateurParDefaut() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("demo");
        utilisateur.setPrenom("demo");
        utilisateur.setMotDePasse("demo1234");
        utilisateur.setEmail("demo@demo.com");
        utilisateurService.save(utilisateur);
    }
}
