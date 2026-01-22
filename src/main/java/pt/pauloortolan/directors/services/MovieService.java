package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.persistence.repositories.MovieRepository;
import pt.pauloortolan.directors.pojo.MoviePojo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Optional<Movie> findMovieByTmdbId(int tmdbId) {
        return movieRepository
                .findByTmdbId(tmdbId);
    }

    public Movie save(Movie movie) {
        return movieRepository
                .save(movie);
    }


    public List<MoviePojo> findByDirector(int directorTmdbId) {
        return movieRepository
                .findByDirectorTmdbId(directorTmdbId)
                .stream()
                .map(movieMapper::fromEntity)
                .filter(m -> m.getReleaseDate() != null)
                .sorted(Comparator.comparing(MoviePojo::getReleaseDate))
                .toList();
    }

    public MoviePojo findMovieById(UUID id) {
        return movieMapper
                .fromEntity(movieRepository.getReferenceById(id));
    }

    public MoviePojo findByTmdbId(int tmdbId) {
        return findMovieByTmdbId(tmdbId)
                .map(movieMapper::fromEntity)
                .orElse(null);
    }

}
