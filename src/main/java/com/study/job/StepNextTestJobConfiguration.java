package com.study.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StepNextTestJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final String JOB_NAME = "stepNextTestJob";
    private final String STEP_NAME = "step";

    @Bean(name = JOB_NAME)
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1())
                .next(step2())
                .next(step3())
                .build();

    }

    @Bean(name = STEP_NAME + "1")
    public Step step1() {
        return stepBuilderFactory.get(STEP_NAME + "1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>> name : {}", STEP_NAME + "1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean(name = STEP_NAME + "2")
    public Step step2() {
        return stepBuilderFactory.get(STEP_NAME + "2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>> name : {}", STEP_NAME + "2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean(name = STEP_NAME + "3")
    public Step step3() {
        return stepBuilderFactory.get(STEP_NAME + "3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>> name : {}", STEP_NAME + "3");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
