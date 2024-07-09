package fr.esgi.calendrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GifDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;

    String url;

    String legende;
}
