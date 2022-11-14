package com.sakanal.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class ApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
//        System.out.println("jdbcTemplate = " + jdbcTemplate);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from `user`");
//        Long aLong = jdbcTemplate.queryForObject("select count(*) from `user`", Long.class);
//        list.forEach(System.out::println);
//        log.info("记录总数："+aLong);
        log.info("数据源类型:"+dataSource.getClass());
    }

}
