package com.example.microservicesmongodb.Service;

import com.example.microservicesmongodb.Document.Site;
import com.example.microservicesmongodb.Repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Optional<Site> getSiteById(UUID id) {
        return siteRepository.findById(id);
    }

    public List<Site> getSitesByNom(String nom) {
        return siteRepository.findByName(nom);
    }

    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    public void deleteSite(UUID id) {
        siteRepository.deleteById(id);
    }
}
