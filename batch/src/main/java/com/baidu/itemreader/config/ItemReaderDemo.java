package com.baidu.itemreader.config;

import com.baidu.batch.listener.MyChunkListener;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ItemReaderDemo  {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderDemoJob(){
        return jobBuilderFactory.get("itemReaderDemoJob1")
                .start(itemReaderDemoStep())
                .build();
    }
    @Bean
    public Step itemReaderDemoStep() {
        return stepBuilderFactory.get("itemReagerDemoStep1")
                .<String,String>chunk(2)
                .faultTolerant()
                .listener(new MyChunkListener())
                .reader(itemReaderDemoRead())
                .writer(list->{
                    for (String item:list){
                        System.out.println(item+",...");
                    }
                }).build();
    }
    @Bean
    public MyReader itemReaderDemoRead() {
        List<String> data = Arrays.asList("cat","dog","pig","duck");
        return new MyReader(data);
    }

//    @BeforeChunk
//    public void beforeStep(StepExecution stepExecution) {
//        System.out.println("before.......");
//    }
//
//    @AfterChunk
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        System.out.println("after.......");
//        return null;
//    }
}
