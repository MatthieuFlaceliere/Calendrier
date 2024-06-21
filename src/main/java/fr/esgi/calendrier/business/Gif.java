package fr.esgi.calendrier.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gif {
    @Id
    @GeneratedValue()
    private Long id;

    @Pattern(regexp = "^.+\\.(?i)(gif)$", message = "L'URL doit se terminer par .gif, .Gif ou .GIF")
    private String url;
}
