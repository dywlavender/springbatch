//package com.baidu.reader;
//
//import com.baidu.reader.dao.Customer;
//import com.baidu.reader.dao.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.annotation.AfterChunk;
//import org.springframework.batch.core.annotation.BeforeChunk;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.validation.BindException;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
////@EnableBatchProcessing
//public class ItemReaderDbDemo {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    @Qualifier(value = "dbJdbcWrite")
//    private ItemWriter<? super User> dbJdbcWrite;
//
//    @Autowired
//    @Qualifier(value = "flatFileWriter")
//    private ItemWriter<? super Customer> flatFileWriter;
//    @Bean
//    public Job itemReaderDbJob2(){
//        return jobBuilderFactory.get("itemReaderDbJob2")
//                .start(itemReaderDbStep())
//                .next(fileItemReaderStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDbStep() {
//        return stepBuilderFactory.get("itemReaderDbStep")
//                .chunk(2)
//                .reader(dbJdbcReader())
//                .writer((ItemWriter<? super Object>) dbJdbcWrite)
//                .build();
//    }
//    @Bean
//
//    public Step fileItemReaderStep() {
//        return stepBuilderFactory.get("fileItemReaderStep")
//                .<Customer,Customer>chunk(2)
//                .reader(flatFileItemReader())
//                .writer(flatFileWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<User> dbJdbcReader(){
//        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<User>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(2);
////        将读取到的记录转换成User对象
//        reader.setRowMapper(new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                User user = new User();
//                user.setId(resultSet.getInt(1));
//                user.setUsername(resultSet.getString(2));
//                user.setPassword(resultSet.getString(3));
//                user.setAge(resultSet.getInt(4));
//                return user;
//            }
//
//        });
////        指定sql语句
//        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
//        provider.setSelectClause("id,username,password,age");
//        provider.setFromClause("from user");
//
//        Map<String, Order> sort = new HashMap<>(1);
//        sort.put("id",Order.ASCENDING);
//        provider.setSortKeys(sort);
//        reader.setQueryProvider(provider);
//        return reader;
//    }
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Customer> flatFileItemReader(){
//        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
//        reader.setResource(new ClassPathResource("customer.txt"));
//        reader.setLinesToSkip(1);//跳过第一行
////        解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames(new String[]{"id","firstName","lastName"});
////        把解析出的一行数据映射为Customer对象
//        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<Customer>();
//        mapper.setLineTokenizer(tokenizer);
//        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
//            @Override
//            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
//                Customer customer = new Customer();
//                customer.setId(fieldSet.readInt("id"));
//                customer.setFirstName(fieldSet.readString("firstName"));
//                customer.setLastName(fieldSet.readString("lastName"));
//
//                return customer;
//            }
//        });
//        mapper.afterPropertiesSet();
//        reader.setLineMapper(mapper);
//        return  reader;
//    }
////    @Bean
////    @StepScope
////    public StaxEventItemReader<Customer> xmlFileReader(){
////        StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
////        reader.setResource(new ClassPathResource("customer.xml"));
//////        指定需要处理的标签
////        reader.setFragmentRootElementName("customer");
//////        把xml转成对象
////        XStreamMarshaller
////    }
//}
