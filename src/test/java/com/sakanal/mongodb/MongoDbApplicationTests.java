package com.sakanal.mongodb;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.sakanal.mongodb.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class MongoDbApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;
    @Test
    void contextLoads() {
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println(db);
        System.out.println(db.getName());
        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }
    }
    @Test
    void insert(){
        Random random = new Random();
        ArrayList<User> users = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(random.nextInt(100)+1);
            user.setName(UUID.randomUUID().toString().replace("-",""));
            user.setAddress(UUID.randomUUID().toString().replace("-",""));
            users.add(user);
        }
        mongoTemplate.insert(users,User.class);
    }

    @Test
    void delete(){
        Query query = new Query(Criteria.where("name").is("tom"));
        System.out.println(mongoTemplate.remove(query, User.class));
    }

    @Test
    void update(){
        UpdateResult updateResult = mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("id").is(2)),
                new Update().set("phone", new Random().nextLong()),
                User.class
        );
        System.out.println(updateResult);
    }

    @Test
    void select(){
        List<User> users = mongoTemplate.find(new Query()
                        .addCriteria(Criteria.where("age").gte(18))
//                        .addCriteria(Criteria.where("name").regex("^.*ab.*$"))
//                        .addCriteria(Criteria.where("id").is(2))
                        .limit(3)
                        .with(Sort.by(Sort.Order.desc("age"))),
                User.class);
        users.forEach(System.out::println);
    }


}
