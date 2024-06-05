package com.example.microservicesmongodb.Controller;

import com.example.microservicesmongodb.Document.Site;
import com.example.microservicesmongodb.Service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable UUID id) {
        Optional<Site> site = siteService.getSiteById(id);
        if (site.isPresent()) {
            return ResponseEntity.ok(site.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nom/{nom}")
    public List<Site> getSitesByNom(@PathVariable String nom) {
        return siteService.getSitesByNom(nom);
    }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable UUID id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}
