package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pauloortolan.directors.persistence.entities.Director;

import java.util.Optional;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {

    Optional<Director> findByTmdbId(int tmdbId);

}
