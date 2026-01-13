package pt.pauloortolan.directors.batch.directors.step1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.pojo.DirectorImportPOJO;

@Slf4j
@Component
public class DirectorImporterReader extends FlatFileItemReader<DirectorImportPOJO> {

    public DirectorImporterReader(@Value("${directors.source.file}") String sourceFile, LineMapper<DirectorImportPOJO> directorImportLineMapper) {
        super(directorImportLineMapper);
        setResource(new FileSystemResource(sourceFile));
        setLinesToSkip(1);
    }

}
