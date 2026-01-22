package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.entities.DirectorMoviePK;

public interface DirectorMovieRepository extends JpaRepository<DirectorMovie, DirectorMoviePK> {
}
