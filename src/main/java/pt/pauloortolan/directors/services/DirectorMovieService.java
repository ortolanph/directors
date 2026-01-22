package pt.pauloortolan.directors.services;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import pt.pauloortolan.directors.mappers.*;
import pt.pauloortolan.directors.persistence.entities.*;
import pt.pauloortolan.directors.persistence.repositories.*;
import pt.pauloortolan.directors.pojo.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorMovieService {

    private final DirectorMovieRepository repository;

    private final DirectorService directorService;

    private final MovieMapper movieMapper;

    public DirectorMovie save(DirectorMovie directorMovie) {
        return repository.save(directorMovie);
    }

    public DirectorResume generateReport(int directorId, List<Integer> movies) {
        DirectorPojo director = directorService.findByTmbdId(directorId);

        if (!Objects.isNull(director)) {
            List<MoviePojo> selectedMovies = repository
                .findAllMoviesByDirectorTmdbId(director.getTmdbId(), movies)
                .stream()
                .filter(movie -> movie.getReleaseDate() != null)
                .sorted(Comparator.comparing(Movie::getReleaseDate))
                .map(movieMapper::fromEntity)
                .toList();

            return DirectorResume
                .builder()
                .name(director.getName())
                .movies(selectedMovies.stream().map(movieMapper::toResume).toList())
                .build();
        }

        return null;
    }
}
