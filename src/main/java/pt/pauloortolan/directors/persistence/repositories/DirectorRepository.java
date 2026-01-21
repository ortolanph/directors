package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.pojo.DirectorPojo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {

    Optional<Director> findByTmdbId(int tmdbId);

    @Query("""
    select dm.director
      from DirectorMovie dm
     where dm.movie.tmdbId = :movieTmdbId
    """)
    List<Director> findDirectorsByMovieTmdbId(int movieTmdbId);
}
