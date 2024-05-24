package fr.esgi.calendrier.mapper;

import fr.esgi.calendrier.business.Utilisateur;
import fr.esgi.calendrier.dto.UtilisateurDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UtilisateurMapper {
    Utilisateur toEntity(UtilisateurDto utilisateurDto);

    UtilisateurDto toDto(Utilisateur utilisateur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Utilisateur partialUpdate(UtilisateurDto utilisateurDto,
                              @MappingTarget Utilisateur utilisateur);
}
