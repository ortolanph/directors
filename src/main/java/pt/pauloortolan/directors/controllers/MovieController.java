package pt.pauloortolan.directors.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.pauloortolan.directors.pojo.MoviePojo;
import pt.pauloortolan.directors.services.MovieService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/director/{directorTmdbId}")
    public List<MoviePojo> moviesFromDirector(@PathVariable("directorTmdbId") int directorTmdbId) {
        return movieService.findByDirector(directorTmdbId);
    }

}
