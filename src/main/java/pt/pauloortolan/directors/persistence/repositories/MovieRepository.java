package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pauloortolan.directors.persistence.entities.Movie;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    Optional<Movie> findByTmdbId(int tmdbId);

}
