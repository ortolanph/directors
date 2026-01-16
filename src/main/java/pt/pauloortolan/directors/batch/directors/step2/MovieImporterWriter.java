package pt.pauloortolan.directors.batch.directors.step2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.persistence.entities.Movie;
import pt.pauloortolan.directors.pojo.Credits;
import pt.pauloortolan.directors.services.DirectorService;
import pt.pauloortolan.directors.services.MovieService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieImporterWriter implements ItemWriter<Credits> {

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    private final DirectorService directorService;

    @Override
    public void write(Chunk<? extends Credits> chunk) throws Exception {
        for(Credits credits : chunk) {
            Director director = directorService.getDirectorById(credits.director().getId());
            Set<Movie> movies = director.getMovies();

            for(Crew crewMovie : credits.movieCredits()) {
                Movie movie = null;
                Set<Director> directors = new HashSet<>();

                Optional<Movie> existingMovie = movieService.findByTmdbId(crewMovie.getId());

                if(existingMovie.isPresent()) {
                    movie = existingMovie.get();
                    directors = movie.getDirectors();
                } else {
                    movie = movieMapper.fromCrew(crewMovie);
                }

                directors.add(director);
                movieService.save(movie);
                movies.add(movie);
            }

            director.setMovies(movies);
            directorService.saveDirector(director);
        }
    }

}
