package fr.esgi.calendrier.business;

import fr.esgi.calendrier.business.customId.ReactionJourId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reaction_jour")
public class ReactionJour {
    @EmbeddedId
    private ReactionJourId id;

    @OneToOne()
    @MapsId("utilisateurId")
    private Utilisateur utilisateur;

    @ManyToOne()
    private Reaction reaction;
}
