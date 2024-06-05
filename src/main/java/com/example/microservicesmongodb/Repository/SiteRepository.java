package com.example.microservicesmongodb.Repository;

import com.example.microservicesmongodb.Document.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SiteRepository extends MongoRepository<Site, UUID> {
    List<Site> findByName(String nom);
}
