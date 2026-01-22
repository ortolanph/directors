package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.pauloortolan.directors.persistence.entities.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    Optional<Movie> findByTmdbId(int tmdbId);

    @Query(
            """
                    select dm.movie
                      from DirectorMovie dm
                     where dm.director.tmdbId = :directorTmdbId
                    """)
    List<Movie> findByDirectorTmdbId(@Param("directorTmdbId") int directorTmdbId);

}
