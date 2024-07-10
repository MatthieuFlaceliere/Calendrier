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

    @MapsId("utilisateurId")
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", insertable = false, updatable = false)
    private Utilisateur utilisateur;

    @MapsId("reactionId")
    @ManyToOne
    @JoinColumn(name = "reaction_id", insertable = false, updatable = false)
    private TypeReaction typeReaction;
}
