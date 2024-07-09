package fr.esgi.calendrier.mapper;

import fr.esgi.calendrier.business.Gif;
import fr.esgi.calendrier.dto.GifDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface GifMapper {
    Gif toEntity(GifDto gifDto);

    GifDto toDto(Gif gif);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Gif partialUpdate(GifDto gifDto,
                      @MappingTarget Gif gif);
}
