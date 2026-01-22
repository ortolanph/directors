package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.repositories.DirectorMovieRepository;
import pt.pauloortolan.directors.pojo.DirectorPojo;
import pt.pauloortolan.directors.pojo.DirectorResume;
import pt.pauloortolan.directors.pojo.MoviePojo;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorMovieService {

    private final DirectorMovieRepository repository;

    private final DirectorService directorService;

    private MovieMapper movieMapper;

    public DirectorMovie save(DirectorMovie directorMovie) {
        return repository.save(directorMovie);
    }

    public DirectorResume generateReport(int directorId, List<Integer> movies) {
        DirectorPojo director = directorService.findByTmbdId(directorId);

        if (!Objects.isNull(director)) {
            List<MoviePojo> selectedMovies = repository
                    .findAllMoviesByDirectorTmdbId(director.getTmdbId(), movies)
                    .stream()
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
