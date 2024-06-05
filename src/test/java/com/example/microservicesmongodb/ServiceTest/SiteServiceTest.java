package com.example.microservicesmongodb.ServiceTest;

import com.example.microservicesmongodb.Document.Site;
import com.example.microservicesmongodb.Repository.SiteRepository;
import com.example.microservicesmongodb.Service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SiteServiceTest {

    @Mock
    private SiteRepository siteRepository;

    @InjectMocks
    private SiteService siteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSiteById() {
        // Given
        UUID id = UUID.randomUUID();
        Site site = new Site();
        site.setId(id);
        when(siteRepository.findById(id)).thenReturn(Optional.of(site));

        // When
        Optional<Site> foundSite = siteService.getSiteById(id);

        // Then
        assertThat(foundSite).isPresent();
        assertThat(foundSite.get().getId()).isEqualTo(id);
    }

    @Test
    public void testGetSitesByNom() {
        // Given
        String nom = "Stade de France";
        Site site = new Site();
        site.setName(nom);
        when(siteRepository.findByName(nom)).thenReturn(Collections.singletonList(site));

        // When
        List<Site> foundSites = siteService.getSitesByNom(nom);

        // Then
        assertThat(foundSites).isNotEmpty();
        assertThat(foundSites.get(0).getName()).isEqualTo(nom);
    }

    @Test
    public void testSaveSite() {
        // Given
        Site site = new Site();
        when(siteRepository.save(any(Site.class))).thenReturn(site);

        // When
        Site savedSite = siteService.saveSite(site);

        // Then
        assertThat(savedSite).isNotNull();
        verify(siteRepository, times(1)).save(site);
    }

    @Test
    public void testDeleteSite() {
        // Given
        UUID id = UUID.randomUUID();
        doNothing().when(siteRepository).deleteById(id);

        // When
        siteService.deleteSite(id);

        // Then
        verify(siteRepository, times(1)).deleteById(id);
    }
}

