package pt.pauloortolan.directors.batch.directors.step1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.pojo.DirectorPojo;
import pt.pauloortolan.directors.services.DirectorService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DirectorImporterWriter implements ItemWriter<DirectorPojo> {

    private final DirectorService service;

    @Override
    public void write(Chunk<? extends DirectorPojo> chunk) throws Exception {
        chunk
                .getItems()
                .stream()
                .filter(pojo -> !service.checkDirector(pojo.getTmdbId()))
                .forEach(service::saveDirector);
    }

}
