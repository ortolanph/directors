package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.mappers.DirectorMapper;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.persistence.repositories.DirectorRepository;
import pt.pauloortolan.directors.pojo.DirectorPojo;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public List<DirectorPojo> getAllDirectorNames() {
        return directorRepository
                .findAll()
                .stream()
                .map(directorMapper::fromEntity)
                .toList();
    }

    public boolean checkDirector(int tmdbId) {
        return directorRepository
                .findByTmdbId(tmdbId)
                .isPresent();
    }


    public DirectorPojo saveDirector(DirectorPojo director) {
        Director directorToSave = directorMapper.fromPojo(director);
        return directorMapper.fromEntity(directorRepository.save(directorToSave));
    }

}
