package fr.esgi.calendrier.business.customId;

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
public class JourId implements Serializable {
    private int jour;
    private int mois;

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourId jourId = (JourId) o;
        return jour == jourId.jour && mois == jourId.mois;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, mois);
    }
}
