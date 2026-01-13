package pt.pauloortolan.directors.batch.directors.step2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.TMDBClient;
import pt.pauloortolan.directors.integration.pojo.AuthResponse;
import pt.pauloortolan.directors.integration.pojo.MovieCredits;
import pt.pauloortolan.directors.persistence.entities.Director;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieImporterProcessor implements ItemProcessor<Director, MovieCredits> {

    private final TMDBClient tmdbClient;

    @Value("${tmdb.api.key}")
    private String language;

    @Override
    public @Nullable MovieCredits process(Director item) throws Exception {
        AuthResponse authResponse = tmdbClient.authenticate();

        log.info("Getting movie credits for director {} (TMDB ID: {})", item.getName(), item.getTmdbId());
        if (authResponse.isSuccess()) {
            return tmdbClient.getMovieCredits(item.getTmdbId(), language);
        } else {
            log.error("Failed to authenticate to TMDB");
        }

        return MovieCredits.builder().build();
    }
}
