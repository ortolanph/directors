package pt.pauloortolan.directors.batch.directors;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import pt.pauloortolan.directors.batch.directors.step1.DirectorImporterProcessor;
import pt.pauloortolan.directors.batch.directors.step1.DirectorImporterReader;
import pt.pauloortolan.directors.batch.directors.step1.DirectorImporterWriter;
import pt.pauloortolan.directors.pojo.DirectorImportPOJO;
import pt.pauloortolan.directors.pojo.DirectorPojo;

@Configuration
@RequiredArgsConstructor
public class DriectorsJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final DirectorImporterReader importerReader;
    private final DirectorImporterProcessor importerProcessor;
    private final DirectorImporterWriter importerWriter;

    @Value("${directors.chunk.size}")
    private int chunkSize;

    @Bean
    public Job directorJob() {
        return new JobBuilder("Directors-Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(importDirectorsStep())
//                .next(importWatchedTVShowData())
//                .next(statisticsMakerTVShowData())
//                .next(aggregatorTVShowData())
                .build();
    }

    @Bean
    public Step importDirectorsStep() {
        return new StepBuilder("Director-Step-Import", jobRepository)
                .<DirectorImportPOJO, DirectorPojo>chunk(chunkSize)
                .reader(importerReader)
                .processor(importerProcessor)
                .writer(importerWriter)
                .build();
    }


}
