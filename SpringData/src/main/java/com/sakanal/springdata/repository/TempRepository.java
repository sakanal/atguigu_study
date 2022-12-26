package com.sakanal.springdata.repository;

import com.sakanal.springdata.bean.TempEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempRepository extends ElasticsearchRepository<TempEntity, Integer> {

    List<TempEntity> findUsersByNameAndAddress(String name, String address);
}
