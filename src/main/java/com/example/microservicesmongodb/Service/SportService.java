package com.example.microservicesmongodb.Service;

import com.example.microservicesmongodb.Document.Sport;
import com.example.microservicesmongodb.Repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Optional<Sport> getSportById(UUID id) {
        return sportRepository.findById(id);
    }

    public List<Sport> getSportsByName(String nom) {
        return sportRepository.findByName(nom);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Sport> getSportsByEventDate(LocalDate date) {
        // Convertissez la LocalDate en Date pour pouvoir l'utiliser dans la requête MongoDB
        Date startDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Créez une requête pour rechercher les sports ayant un événement à la date spécifiée
        Query query = new Query();
        query.addCriteria(Criteria.where("events.date").gte(startDate).lt(endDate));
        // Exécutez la requête et retournez les résultats
        return mongoTemplate.find(query, Sport.class);
    }

    public Sport saveSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public void deleteSport(UUID id) {
        sportRepository.deleteById(id);
    }

}
