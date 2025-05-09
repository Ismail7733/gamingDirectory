package com.ismailo.gamingDirectory.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailo.gamingDirectory.*;
import com.ismailo.gamingDirectory.domain.*;
import com.ismailo.gamingDirectory.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GamerGameLinkControllerIntegrationTest {

    private MockMvc mockMvc;
    private GamerRepository gamerRepository;
    private GameRepository gameRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public GamerGameLinkControllerIntegrationTest(GamerRepository gamerRepository, GameRepository gameRepository, MockMvc mockMvc) {
        this.gamerRepository = gamerRepository;
        this.gameRepository = gameRepository;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }


    @Test
    public void testThatGamerGameLinkReturnsHttp201Created() throws Exception {
        GameEntity gameEntityA = TestUtil.getGameEntityA();
        gameRepository.save(gameEntityA);
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();
        gamerRepository.save(gamerEntityA);

        GamerGameId gamerGameId = new GamerGameId(gameEntityA.getId(), gamerEntityA.getId());
        GamerGameLink gamerGameLink = new GamerGameLink(gamerGameId, Level.N00B);

        String gamerGameLinkJson = objectMapper.writeValueAsString(gamerGameLink);

        // Perform the request
        mockMvc.perform(
                post("/gamer-game-links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerGameLinkJson)
        ).andExpect(status().isCreated());
    }

    @Test
    public void testThatMissingGamerReturnsHttp400() throws Exception {
        GameEntity gameEntityA = TestUtil.getGameEntityA();
        gameRepository.save(gameEntityA);

        GamerGameId gamerGameId = new GamerGameId(gameEntityA.getId(), 100L);
        GamerGameLink gamerGameLink = new GamerGameLink(gamerGameId, Level.N00B);

        String gamerGameLinkJson = objectMapper.writeValueAsString(gamerGameLink);

        mockMvc.perform(
                post("/gamer-game-links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerGameLinkJson)
                ).andExpect(status().isBadRequest())
                .andExpect(content().string("Gamer with ID 100 does not exist."));
    }

    @Test
    public void testThatMissingGameReturnsHttp400() throws Exception {
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();
        gamerRepository.save(gamerEntityA);

        GamerGameId gamerGameId = new GamerGameId(100L, gamerEntityA.getId());
        GamerGameLink gamerGameLink = new GamerGameLink(gamerGameId, Level.N00B);

        String gamerGameLinkJson = objectMapper.writeValueAsString(gamerGameLink);

        mockMvc.perform(
                post("/gamer-game-links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerGameLinkJson)
                ).andExpect(status().isBadRequest())
                .andExpect(content().string("Game with ID 100 does not exist."));
    }
}