package pt.pauloortolan.directors.batch.directors.step2;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;
import pt.pauloortolan.directors.persistence.entities.Director;

@Slf4j
@Component
public class MovieImporterReader extends JpaPagingItemReader<Director> {

    public MovieImporterReader(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
        setQueryString("SELECT d FROM Director d");
    }
}
