package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.*;
import pt.pauloortolan.directors.persistence.entities.*;

public interface DirectorMovieRepository extends JpaRepository<DirectorMovie, DirectorMoviePK> {
}
