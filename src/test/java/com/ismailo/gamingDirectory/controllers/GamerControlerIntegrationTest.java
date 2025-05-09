package com.ismailo.gamingDirectory.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailo.gamingDirectory.TestUtil;
import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.repositories.GamerRepositoryIntegrationTest;
import com.ismailo.gamingDirectory.services.GamerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class GamerControlerIntegrationTest {
    private GamerService gamerService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public GamerControlerIntegrationTest(GamerService gamerService, MockMvc mockMvc) {
        this.gamerService = gamerService;
        this.mockMvc = mockMvc;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatGamerSuccessfullyReturnsHttp201Created() throws Exception {
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();

        String gamerJson = objectMapper.writeValueAsString(gamerEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/gamers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateGamerSuccessfullyReturnsSavedGamer() throws Exception {
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();

        String gamerJson = objectMapper.writeValueAsString(gamerEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/gamers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.country").value("Denmark")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatGamerCanBeFoundAfterBeingCreated() throws Exception {
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();

        gamerService.createGamer(gamerEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/gamers/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.country").value("Denmark")
        );
    }


}
