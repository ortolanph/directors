package pt.pauloortolan.directors.batch.directors.step1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.mappers.DirectorMapper;
import pt.pauloortolan.directors.pojo.DirectorImportPOJO;
import pt.pauloortolan.directors.pojo.DirectorPojo;

@Slf4j
@Component
@RequiredArgsConstructor
public class DirectorImporterProcessor implements ItemProcessor<DirectorImportPOJO, DirectorPojo> {

    private final DirectorMapper directorMapper;

    @Override
    public @Nullable DirectorPojo process(DirectorImportPOJO item) throws Exception {
        return directorMapper.fromImport(item);
    }

}
