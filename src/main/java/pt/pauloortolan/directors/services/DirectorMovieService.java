package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.persistence.repositories.DirectorMovieRepository;
import pt.pauloortolan.directors.pojo.DirectorPojo;
import pt.pauloortolan.directors.pojo.DirectorResume;
import pt.pauloortolan.directors.pojo.MoviePojo;
import pt.pauloortolan.directors.pojo.MovieResume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorMovieService {

    private final DirectorMovieRepository repository;

    private final DirectorService directorService;

    private final MovieMapper movieMapper;

    private final PosterService posterService;

    public DirectorMovie save(DirectorMovie directorMovie) {
        return repository.save(directorMovie);
    }

    public DirectorResume generateReport(int directorId, List<Integer> movies) {
        DirectorPojo director = directorService.findByTmbdId(directorId);

        if (!Objects.isNull(director)) {
            List<Movie> selectedDirectorsMovie = new ArrayList<>();

            if (movies.isEmpty()) {
                selectedDirectorsMovie = repository
                        .findAllMoviesByDirectorTmdbId(directorId);

            } else {
                selectedDirectorsMovie = repository
                        .findMoviesByDirectorTmdbId(director.getTmdbId(), movies);

            }

            List<MoviePojo> selectedMovies = selectedDirectorsMovie.stream()
                    .filter(movie -> movie.getReleaseDate() != null)
                    .sorted(Comparator.comparing(Movie::getReleaseDate))
                    .map(movieMapper::fromEntity)
                    .toList();

            return DirectorResume
                    .builder()
                    .name(director.getName())
                    .movies(
                            selectedMovies
                                    .stream()
                                    .map(movieMapper::toResume)
                                    .map(downloadPosterFunction())
                                    .toList())
                    .build();
        }

        return null;
    }

    private Function<MovieResume, MovieResume> downloadPosterFunction() {
        return (input) ->
        {
            try {
                return MovieResume
                        .builder()
                        .title(input.getTitle())
                        .poster(
                                (Objects.isNull(input.getPoster())) ?
                                        posterService.convertLocalImageToBase64(posterService.getRandomSurrogatePoster()) :
                                        posterService.convertImageToBase64(input.getPoster()))
                        .releaseDate(input.getReleaseDate())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
