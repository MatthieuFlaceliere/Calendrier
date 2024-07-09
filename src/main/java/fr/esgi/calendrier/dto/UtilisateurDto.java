package fr.esgi.calendrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
    String email;

    @NotNull
    String password;
    
    @NotNull
    String theme;
}