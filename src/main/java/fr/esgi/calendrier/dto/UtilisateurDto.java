package fr.esgi.calendrier.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {
    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private String prenom;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String motDePasse;

    @NotNull
    @NotEmpty
    private String theme;
}