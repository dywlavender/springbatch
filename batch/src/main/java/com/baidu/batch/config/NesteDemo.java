//package com.baidu.batch.config;
//
//import jdk.nashorn.internal.scripts.JO;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//public class NesteDemo {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private Job childJobOne;
//    @Autowired
//    private Job childJobTwo;
//    @Autowired
//    private JobLauncher launcher;
//    @Bean
//    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager){
//        return jobBuilderFactory.get("parentJob")
//                .start(childJob1(repository,transactionManager))
//                .next(childJob2(repository,transactionManager))
//                .build();
//    }
//
//    private Step childJob2(JobRepository repository,PlatformTransactionManager transactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJob2"))
//                .job(childJobTwo)
//                .repository(repository)
//                .transactionManager(transactionManager)
//                .build();
//    }
//
//    private Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJob1"))
//                .job(childJobOne)
//                .repository(repository)
//                .transactionManager(transactionManager)
//                .launcher(launcher).build();
//    }
//
//}
