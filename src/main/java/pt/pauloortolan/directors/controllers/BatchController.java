package pt.pauloortolan.directors.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/batches")
@RequiredArgsConstructor
public class BatchController {

    private final JobOperator jobOperator;
    private final Job directorJob;

    @GetMapping("/directors")
    public String startDirectorsJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startedAt", System.currentTimeMillis())
                    .addString("executionId", UUID.randomUUID().toString())
                    .toJobParameters();

            JobExecution execution = jobOperator.start(directorJob, jobParameters);
            return execution.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
