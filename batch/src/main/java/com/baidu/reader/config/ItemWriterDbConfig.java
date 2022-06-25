package com.baidu.reader.config;

import com.baidu.reader.dao.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class ItemWriterDbConfig {
    @Autowired
    private  DataSource dataSource;
    @Bean
    public JdbcBatchItemWriter<Customer> itemWriterDb(){
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into customer(id, firstname,lastname) values "+
                "(:id,:firstname,:lastname)") ;
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Bean
//    public Step retryDemoStep(){
//        return stepBuilderFactory.get("retryDemoStep")
//                .<String,String>chunk(10)
//                .reader(reader())
//                .processor(retryItemProcessor)
//                .writer(retryItemWriter)
//                .faultTolerant()
//                .retry(CustomRetryException.class)
//                .retryLimit(10)
//                .build();
//    }

}
