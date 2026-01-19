package pt.pauloortolan.directors.batch.directors.step2;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.batch.infrastructure.item.*;
import org.springframework.stereotype.*;
import pt.pauloortolan.directors.integration.pojo.*;
import pt.pauloortolan.directors.mappers.*;
import pt.pauloortolan.directors.persistence.entities.*;
import pt.pauloortolan.directors.pojo.*;
import pt.pauloortolan.directors.services.*;

import java.time.*;
import java.util.*;

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
                Optional<Movie> existingMovie = movieService.findByTmdbId(movie.getTmdbId());

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
