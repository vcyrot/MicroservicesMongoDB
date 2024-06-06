package com.example.microservicesmongodb.Controller;

import com.example.microservicesmongodb.Document.Sport;
import com.example.microservicesmongodb.Service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/mongo/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping
    public List<Sport> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSportById(@PathVariable UUID id) {
        Optional<Sport> sport = sportService.getSportById(id);
        if (sport.isPresent()) {
            return ResponseEntity.ok(sport.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nom/{nom}")
    public List<Sport> getSportsByName(@PathVariable String nom) {
        return sportService.getSportsByName(nom);
    }

    @GetMapping("/event-date/{date}")
    public List<Sport> getSportsByEventDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return sportService.getSportsByEventDate(localDate);
    }

    @PostMapping
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSport(@PathVariable UUID id) {
        sportService.deleteSport(id);
        return ResponseEntity.noContent().build();
    }
}
