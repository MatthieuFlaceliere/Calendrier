package fr.esgi.calendrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class GifDto {

    @Pattern(regexp = "^.+\\.(?i)(gif)$", message = "L'URL doit se terminer par .gif, .Gif ou .GIF")
    @NotNull
    public String url;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @Length(max = 128, message = "La légende doit faire moins de 128 caractères")
    String legende;
}
