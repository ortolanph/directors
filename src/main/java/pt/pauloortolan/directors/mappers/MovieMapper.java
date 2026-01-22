package pt.pauloortolan.directors.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.pojo.MoviePojo;
import pt.pauloortolan.directors.pojo.MovieResume;

import java.time.LocalDate;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {

    String POSTER_PATH_TEMPLATE = "https://image.tmdb.org/t/p/original/%s";

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "tmdbId")
    @Mapping(source = "releaseDate", target = "releaseDate", qualifiedByName = "formatDate")
    @Mapping(target = "directorMovies", ignore = true)
    Movie fromCrew(Crew crew);

    MoviePojo fromEntity(Movie movie);

    @Mapping(source = "posterPath", target = "poster", qualifiedByName = "formatPoster")
    MovieResume toResume(MoviePojo moviePojo);

    @Named("formatDate")
    default LocalDate formatDate(String releaseDate) {
        return releaseDate != null && !releaseDate.isEmpty() ? LocalDate.parse(releaseDate) : null;
    }

    @Named("formatPoster")
    default String formatPoster(String posterPath) {
        return posterPath != null && !posterPath.isEmpty() ? POSTER_PATH_TEMPLATE.formatted(posterPath) : null;
    }
}
