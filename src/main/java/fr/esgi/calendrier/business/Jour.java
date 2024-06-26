package fr.esgi.calendrier.business;

import fr.esgi.calendrier.business.customId.JourId;
import fr.esgi.calendrier.business.customId.ReactionJourId;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jour {
    @EmbeddedId
    private JourId id;

    @OneToOne()
    @Nullable()
    private Gif gif;

    @ManyToOne()
    @Nullable()
    private Utilisateur utilisateur;

    @OneToMany()
    @JoinColumns({
            @JoinColumn(name = "jour", referencedColumnName = "jour"),
            @JoinColumn(name = "mois", referencedColumnName = "mois")
    })
    private List<ReactionJour> reactions = new ArrayList<>();

    private int points;

    public String date() {
        String jour = String.valueOf(id.getJour());
        String mois = String.valueOf(id.getMois());

        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return jour + "/" + mois;
    }

    public int getNbReactionByReaction(Reaction reaction) {
        return (int) reactions.stream().filter(reactionJour -> reactionJour.getId().getReactionId().equals(reaction.getId())).count();
    }

    public List<String> getUsernamesByReaction(Reaction reaction) {
        List<String> usernames = new ArrayList<>();
        reactions.stream().filter(
                reactionJour -> reactionJour.getId().getReactionId().equals(reaction.getId())
        ).forEach(
                reactionJour -> usernames.add(reactionJour.getUtilisateur().nom)
        );
        return usernames;
    }

    public boolean hasReactionByUser(Reaction reaction, Utilisateur utilisateur) {
        boolean r = reactions.stream().anyMatch(
                reactionJour -> reactionJour.getId().getReactionId().equals(reaction.getId()) && reactionJour.getId().getUtilisateurId().equals(utilisateur.getId())
        );
        return r;
    }
}
