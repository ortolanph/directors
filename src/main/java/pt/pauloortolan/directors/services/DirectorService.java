package pt.pauloortolan.directors.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.pauloortolan.directors.mappers.DirectorMapper;
import pt.pauloortolan.directors.persistence.entities.Director;
import pt.pauloortolan.directors.persistence.repositories.DirectorRepository;
import pt.pauloortolan.directors.pojo.DirectorPojo;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public boolean checkDirector(int tmdbId) {
        return directorRepository
                .findByTmdbId(tmdbId)
                .isPresent();
    }

    public void saveDirector(DirectorPojo director) {
        Director directorToSave = directorMapper.fromPojo(director);
        directorMapper.fromEntity(directorRepository.save(directorToSave));
    }

    public Director getDirectorById(UUID id) {
        return directorRepository.getReferenceById(id);
    }

    public List<DirectorPojo> allDirectors() {
        return directorRepository.findAll().stream().map(directorMapper::fromEntity).toList();
    }

    public List<DirectorPojo> findByMovie(int movieTmdbId) {
        return directorRepository
                .findDirectorsByMovieTmdbId(movieTmdbId)
                .stream()
                .map(directorMapper::fromEntity)
                .toList();
    }

    public DirectorPojo findDirectorById(UUID id) {
        return directorMapper.fromEntity(directorRepository.getReferenceById(id));
    }
}
