package com.example.microservicesmongodb.Repository;

import com.example.microservicesmongodb.Document.Sport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SportRepository extends MongoRepository<Sport, UUID> {
    List<Sport> findByName(String nom);
    List<Sport> findByEventsDate(LocalDateTime date);
}


