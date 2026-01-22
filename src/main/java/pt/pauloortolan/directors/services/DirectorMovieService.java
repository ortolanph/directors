package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.persistence.entities.DirectorMovie;
import pt.pauloortolan.directors.persistence.repositories.DirectorMovieRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorMovieService {

    private final DirectorMovieRepository repository;

    public DirectorMovie save(DirectorMovie directorMovie) {
        return repository.save(directorMovie);
    }
}
