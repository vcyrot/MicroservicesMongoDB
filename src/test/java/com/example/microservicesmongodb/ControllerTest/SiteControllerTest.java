package com.example.microservicesmongodb.ControllerTest;

import com.example.microservicesmongodb.Controller.SiteController;
import com.example.microservicesmongodb.Document.Site;
import com.example.microservicesmongodb.Service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SiteController.class)
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    private Site site;

    @BeforeEach
    public void setUp() {
        site = new Site();
        site.setId(UUID.randomUUID());
        site.setName("Stade de France");
    }

    @Test
    public void testGetAllSites() throws Exception {
        when(siteService.getAllSites()).thenReturn(Collections.singletonList(site));

        mockMvc.perform(get("/api/sites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Stade de France"));
    }

    @Test
    public void testGetSiteById() throws Exception {
        UUID id = site.getId();
        when(siteService.getSiteById(id)).thenReturn(Optional.of(site));

        mockMvc.perform(get("/api/sites/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Stade de France"));
    }

    @Test
    public void testGetSitesByNom() throws Exception {
        String nom = site.getName();
        when(siteService.getSitesByNom(nom)).thenReturn(Collections.singletonList(site));

        mockMvc.perform(get("/api/sites/nom/{nom}", nom))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Stade de France"));
    }

    @Test
    public void testCreateSite() throws Exception {
        when(siteService.saveSite(any(Site.class))).thenReturn(site);

        mockMvc.perform(post("/api/sites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\": \"Stade de France\", \"location\": {\"latitude\": 48.924459, \"longitude\": 2.360169}, \"isParalympic\": true, \"sports\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Stade de France"));
    }

    @Test
    public void testDeleteSite() throws Exception {
        UUID id = site.getId();
        when(siteService.getSiteById(id)).thenReturn(Optional.of(site));

        mockMvc.perform(delete("/api/sites/{id}", id))
                .andExpect(status().isNoContent());
    }
}

