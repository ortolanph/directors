package pt.pauloortolan.directors.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.persistence.entities.Movie;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "tmdbId")
    @Mapping(source = "releaseDate", target = "releaseDate", dateFormat = "yyyy-MM-dd")
    Movie fromCrew(Crew crew);

}
