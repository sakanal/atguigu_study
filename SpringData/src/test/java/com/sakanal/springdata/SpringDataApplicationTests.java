package com.sakanal.springdata;

import com.sakanal.springdata.bean.TempEntity;
import com.sakanal.springdata.repository.TempRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
class SpringDataApplicationTests {
    @Autowired
    private TempRepository tempRepository;

    @Test
    void contextLoads() throws IOException {
        TempEntity entity = new TempEntity();
        entity.setId(1);
        entity.setName("张三");
        entity.setAge(18);
        entity.setAddress("江西南昌");
        entity.setCreateTime(LocalDateTime.now());
        System.out.println(entity);

        tempRepository.save(entity);

        Iterable<TempEntity> all = tempRepository.findAll();
        all.forEach(System.out::println);
    }

}
