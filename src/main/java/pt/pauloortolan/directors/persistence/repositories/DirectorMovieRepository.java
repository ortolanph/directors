package pt.pauloortolan.directors.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.entities.DirectorMoviePK;
import pt.pauloortolan.directors.persistence.entities.Movie;

import java.util.List;

public interface DirectorMovieRepository extends JpaRepository<DirectorMovie, DirectorMoviePK> {

    @Query("""
            select dm.movie
              from DirectorMovie dm
             where dm.director.tmdbId = :tmdbId
               and dm.movie.tmdbId in :movieIds
            """)
    List<Movie> findMoviesByDirectorTmdbId(int tmdbId, List<Integer> movieIds);

    @Query("""
            select dm.movie
              from DirectorMovie dm
             where dm.director.tmdbId = :tmdbId
            """)
    List<Movie> findAllMoviesByDirectorTmdbId(int tmdbId);
}
