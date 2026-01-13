package pt.pauloortolan.directors.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pt.pauloortolan.directors.integration.pojo.MovieCredits;
import pt.pauloortolan.directors.integration.pojo.AuthResponse;

@FeignClient(name = "tmdb-client", url = "${tmdb.base-url}", configuration = TMDBFeignConfig.class)
public interface TMDBClient {

    @GetMapping("/authentication")
    AuthResponse authenticate();

    @GetMapping("/person/{directorId}/movie_credits")
    MovieCredits getMovieCredits(@PathVariable("directorId") int directorId,
                                 @RequestParam(value = "language", defaultValue = "en-US") String language);

}
