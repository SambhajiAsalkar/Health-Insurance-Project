package com.sa.config;

import java.util.Collections;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.sa.entity.Elige_DetailsEntity;
import com.sa.repository.EligeRepository;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private EligeRepository eligRepo;

    @Value("${csv.file.path}")
    private String csvFilePath;

    // **ItemReader**: Fetches `Elige_DetailsEntity` from the database
    @Bean(name="reader")
    public RepositoryItemReader<Elige_DetailsEntity> reader() {
        RepositoryItemReader<Elige_DetailsEntity> reader = new RepositoryItemReaderBuilder<Elige_DetailsEntity>()
                .name("eligibilityReader")
                .repository(eligRepo) // Use the repository you created
                .methodName("findAll") // This will call the `findAll` method from your repository
                .arguments(Collections.emptyList()) // No arguments needed for findAll
                .sorts(Map.of("caseNum",Sort.Direction.ASC)) // Sorting by createdDate, ascending
                .build();
        return reader;
    }

    @Bean(name="writer")
    public FlatFileItemWriter<Elige_DetailsEntity> writer() {
        return new FlatFileItemWriterBuilder<Elige_DetailsEntity>()
                .name("citizenItemWriter")
                .resource(new FileSystemResource(csvFilePath))
                .append(true)
                .delimited()
                .delimiter(",")
                .names("caseNum", "planName", "holderName", "holderSsn", "planStatus", "startDate", "endDate", "benifitAmt", "denielReason", "bankName", "accNo")
                .build();
    }


    @Bean
    public ItemProcessor<Elige_DetailsEntity, Elige_DetailsEntity> edDetailsProcessor() {
        return item -> {
            if ((item.getPlanStatus().equalsIgnoreCase("Approved"))) {
                Elige_DetailsEntity details = new Elige_DetailsEntity();
                BeanUtils.copyProperties(item, details);
                return details;
            }
            return null;
        };
    }


    // **Step**: Defines the chunk size and processing flow for each step in the job
    @Bean(name="step1")
    public Step createStep1(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1",jobRepository)
                .<Elige_DetailsEntity, Elige_DetailsEntity>chunk(3,transactionManager)
                .reader(reader())
                .processor(edDetailsProcessor())
                .writer(writer())
                .build();
    }

    // **Job**: The main job definition that contains the step
    @Bean (name="job1")
    public Job creatJob(JobRepository jobRepository,Step step1) {
        return new JobBuilder("job1",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }
}
