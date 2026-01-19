package pt.pauloortolan.directors.batch.directors.step2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.TMDBClient;
import pt.pauloortolan.directors.integration.pojo.AuthResponse;
import pt.pauloortolan.directors.integration.pojo.Crew;
import pt.pauloortolan.directors.integration.pojo.MovieCredits;
import pt.pauloortolan.directors.mappers.DirectorMapper;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.pojo.Credits;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieImporterProcessor implements ItemProcessor<Director, Credits> {

    public static final String DIRECTING = "Directing";
    public static final Predicate<Crew> DEPARTMENT_PREDICATE = crew -> DIRECTING.equals(crew.getDepartment());

    public static final String DIRECTOR = "Director";
    public static final Predicate<Crew> JOB_PREDICATE = crew -> DIRECTOR.equals(crew.getJob());

    private final TMDBClient tmdbClient;

    private final DirectorMapper directorMapper;

    @Value("${tmdb.parameters.language:en-US}")
    private String language;

    @Override
    public @Nullable Credits process(Director item) {
        AuthResponse authResponse = tmdbClient.authenticate();

        log.info("Getting movie credits for director {} (TMDB ID: {})", item.getName(), item.getTmdbId());
        if (authResponse.isSuccess()) {
            MovieCredits movieCredits = tmdbClient.getMovieCredits(item.getTmdbId(), language);

            List<Crew> directingCredits = movieCredits
                    .getCrew()
                    .stream()
                    .filter(DEPARTMENT_PREDICATE)
                    .filter(JOB_PREDICATE)
                    .toList();

            return new Credits(directorMapper.fromEntity(item), directingCredits);
        } else {
            log.error("Failed to authenticate to TMDB");
        }

        return null;
    }
}
