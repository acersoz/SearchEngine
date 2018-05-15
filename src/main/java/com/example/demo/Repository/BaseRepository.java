package com.example.demo.Repository;

import com.example.demo.Model.Domain.Base;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BaseRepository extends MongoRepository<Base, String> {
    List<Base> findByTextLike(String searchText);
}