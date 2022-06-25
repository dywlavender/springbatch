//package com.baidu.batch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.servlet.view.script.ScriptTemplateConfig;
//
//@Configuration
//@EnableBatchProcessing
//public class SplitDemo {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Step splitStep1(){
//        return stepBuilderFactory.get("splitStep11")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("splitStep1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//    @Bean
//    public Step splitStep2(){
//        return stepBuilderFactory.get("splitStep21")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("splitStep2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//    @Bean
//    public Step splitStep3(){
//        return stepBuilderFactory.get("splitStep31")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("splitStep3");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//    @Bean
//    public Flow splitFlow1(){
//        return new FlowBuilder<Flow>("splitFlow11")
//                .start(splitStep1())
//                .build();
//    }
//    @Bean
//    public Flow splitFlow2(){
//        return new FlowBuilder<Flow>("splitFlow21")
//                .start(splitStep2())
//                .from(splitStep2()).on("COMPLETED")
//                .to(splitStep3())
////                .next(splitStep3())
//                .build();
//    }
//    @Bean
//    public Job splitJob(){
//        return jobBuilderFactory.get("splitJob11")
//                .start(splitFlow1())
//                .split(new SimpleAsyncTaskExecutor()).add(splitFlow2())
//                .end().build();
//    }
//}
