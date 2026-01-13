package pt.pauloortolan.directors.batch.directors.step2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.integration.pojo.MovieCredits;
import pt.pauloortolan.directors.mappers.MovieMapper;
import pt.pauloortolan.directors.services.MovieService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieImporterWriter implements ItemWriter<MovieCredits> {

    public static final String DIRECTING = "Directing";
    public static final String DIRECTOR = "Director";

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    @Override
    public void write(Chunk<? extends MovieCredits> chunk) throws Exception {
        chunk
                .getItems()
                .stream()
                .map(MovieCredits::getCrew)
                .flatMap(List::stream)
                .filter(crew -> !crew.isAdult())
                .filter(crew -> DIRECTING.equals(crew.getDepartment()))
                .filter(crew -> DIRECTOR.equals(crew.getJob()))
                .filter(crew -> !movieService.exists(crew.getId()))
                .map(movieMapper::fromCrew)
                .forEach(movieService::save);
    }

}
