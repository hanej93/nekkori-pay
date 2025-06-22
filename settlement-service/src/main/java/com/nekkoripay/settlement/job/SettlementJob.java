package com.nekkoripay.settlement.job;

import com.nekkoripay.settlement.tasklet.SettlementTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@RequiredArgsConstructor
public class SettlementJob {

    private final SettlementTasklet settlementTasklet;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job settlement() {
        return new JobBuilder("settlement", jobRepository)
                .start(settlementStep())
                .build();
    }

    public Step settlementStep() {
        return new StepBuilder("settlementStep", jobRepository)
                .tasklet(settlementTasklet, transactionManager)
                .build();
    }
}
