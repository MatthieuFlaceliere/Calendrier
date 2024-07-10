package fr.esgi.calendrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Value
public class UtilisateurDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    @NotNull
    String nom;

    @NotNull
    String prenom;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@esgi.fr", message = "L'email doit être valide et se terminer par @esgi.fr")
    String email;

    @NotNull
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 3 caractères")
    String password;

    @NotNull
    String theme;
}