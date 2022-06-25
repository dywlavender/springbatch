package com.baidu.reader;

import com.baidu.reader.dao.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbJdbcWrite")
public class DbJdbcWrite implements ItemWriter<User> {

    @Override
    public void write(List<? extends User> items) throws Exception {
        for (User user :
                items) {
            System.out.println(user);
        }
    }
}
