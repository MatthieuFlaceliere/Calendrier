package fr.esgi.calendrier.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gif {
    @Id
    @GeneratedValue()
    private Long id;

    @Pattern(regexp = "^.+\\.(?i)(gif)$", message = "L'URL doit se terminer par .gif, .Gif ou .GIF")
    @NonNull()
    private String url;

    @Length(max = 128, message = "La légende doit faire moins de 128 caractères")
    private String legende;
}
