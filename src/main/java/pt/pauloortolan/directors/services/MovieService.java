package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.persistence.repositories.MovieRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Optional<Movie> findByTmdbId(int tmdbId) {
        return movieRepository.findByTmdbId(tmdbId);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }


}
