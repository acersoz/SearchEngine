package com.example.demo.Repository;

import com.example.demo.Model.Domain.Base;
import com.example.demo.Model.Domain.ScheduledBase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduledBaseRepository extends MongoRepository<ScheduledBase, String> {
    List<Base> findByTextLike(String searchText);
}