package fr.esgi.calendrier.business.customId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionJourId implements Serializable {
    @Column(name = "jour_id")
    private JourId jourId;
    @Column(name = "utilisateur_id")
    private Long utilisateurId;

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionJourId reactionJourId = (ReactionJourId) o;
        return jourId.equals(reactionJourId.jourId) && utilisateurId.equals(reactionJourId.utilisateurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jourId, utilisateurId);
    }
}
