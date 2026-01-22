package pt.pauloortolan.directors.batch.directors.step2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.entities.DirectorMoviePK;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.pojo.Credits;
import pt.pauloortolan.directors.services.DirectorMovieService;
import pt.pauloortolan.directors.services.DirectorService;
import pt.pauloortolan.directors.services.MovieService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieImporterWriter implements ItemWriter<Credits> {

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    private final DirectorService directorService;

    private final DirectorMovieService directorMovieService;

    @Override
    public void write(Chunk<? extends Credits> chunk) {
        for (Credits credit : chunk.getItems()) {
            Director director = directorService.getDirectorById(credit.director().getId());

            for (Crew crewMovie : credit.movieCredits()) {
                Movie movie = movieMapper.fromCrew(crewMovie);
                Optional<Movie> existingMovie = movieService.findMovieByTmdbId(movie.getTmdbId());

                if (existingMovie.isPresent()) {
                    movie = existingMovie.get();
                } else {
                    movie = movieService.save(movie);
                }

                DirectorMoviePK directorMoviePK = DirectorMoviePK
                        .builder()
                        .directorId(director.getId())
                        .movieId(movie.getId())
                        .build();

                DirectorMovie directorMovie = DirectorMovie
                        .builder()
                        .id(directorMoviePK)
                        .director(director)
                        .movie(movie)
                        .updatedAt(LocalDateTime.now())
                        .build();

                directorMovieService.save(directorMovie);
            }
        }
    }

}
