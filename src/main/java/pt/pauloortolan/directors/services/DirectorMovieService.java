package pt.pauloortolan.directors.services;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import pt.pauloortolan.directors.persistence.entities.*;
import pt.pauloortolan.directors.persistence.repositories.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorMovieService {

    private final DirectorMovieRepository repository;

    public DirectorMovie save(DirectorMovie directorMovie) {
        return repository.save(directorMovie);
    }
}
