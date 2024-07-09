package fr.esgi.calendrier.mapper;

import fr.esgi.calendrier.business.Jour;
import fr.esgi.calendrier.dto.JourDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface JourMapper {
    Jour toEntity(JourDto jourDto);

    JourDto toDto(Jour jour);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jour partialUpdate(JourDto jourDto,
                       @MappingTarget Jour jour);
}
