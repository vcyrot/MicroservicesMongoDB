package com.example.microservicesmongodb.RepositoryTest;

import com.example.microservicesmongodb.Document.Site;
import com.example.microservicesmongodb.Repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class SiteRepositoryTest {

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void testFindByNom() {
        // Given
        Site site = new Site();
        site.setId(UUID.randomUUID());
        site.setName("Stade de France");
        siteRepository.save(site);

        // When
        List<Site> foundSites = siteRepository.findByName("Stade de France");

        // Then
        assertThat(foundSites).isNotEmpty();
        assertThat(foundSites.get(0).getName()).isEqualTo("Stade de France");
    }
}

