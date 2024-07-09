package fr.esgi.calendrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.business.ReactionJour;
import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.business.customId.JourId;
import lombok.Value;

import java.util.List;

@Value
public class JourUpdateDto {
    Gif gif;

    Utilisateur utilisateur;

    List<ReactionJour> reactions;

    int points;
}
