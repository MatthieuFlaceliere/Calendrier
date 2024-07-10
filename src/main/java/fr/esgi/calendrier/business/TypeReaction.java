package fr.esgi.calendrier.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reaction_sequence")
    @SequenceGenerator(name = "reaction_sequence")
    private Long id;
    private String unicode;
}
