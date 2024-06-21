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

    @Nullable()
    private String legende;

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
}
