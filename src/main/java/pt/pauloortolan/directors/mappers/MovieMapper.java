package pt.pauloortolan.directors.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.persistence.entities.Movie;

import java.time.LocalDate;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "tmdbId")
    @Mapping(source = "releaseDate", target = "releaseDate",  qualifiedByName = "formatDate")
    Movie fromCrew(Crew crew);

    @Named("formatDate")
    default LocalDate formatDate(String releaseDate) {
        return releaseDate != null && !releaseDate.isEmpty() ? LocalDate.parse(releaseDate) : null;
    }
}
