package pt.pauloortolan.directors.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.pauloortolan.directors.pojo.DirectorPojo;
import pt.pauloortolan.directors.services.DirectorService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
public class DirectorsController {

    private final DirectorService directorService;

    @GetMapping
    List<DirectorPojo> allDirectors() {
        return directorService.allDirectors();
    }

    @GetMapping("/movie/{movieTmdbId}")
    public List<DirectorPojo> directorFromMovie(@PathVariable("movieTmdbId") int movieTmdbId) {
        return directorService.findByMovie(movieTmdbId);
    }

    @GetMapping("/{id}")
    public DirectorPojo findDirectorById(@PathVariable("id") UUID id) {
        return directorService.findDirectorById(id);
    }

    @GetMapping("/tmdbId/{tmdbId}")
    public DirectorPojo findDirectorByIdTmdb(@PathVariable("tmdbId") int id) {
        return directorService.findByTmbdId(id);
    }

    @GetMapping("/name")
    public List<DirectorPojo> findDirectorsByNameLike(@RequestParam("nameLike") String nameLike) {
        return directorService.findDirectorsByNameLike(nameLike);
    }
}
