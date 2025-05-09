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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GamerGameLinkControllerIntegrationTest {

    private MockMvc mockMvc;
    private GamerRepository gamerRepository;
    private GameRepository gameRepository;
    private GamerGameLinkRepository gamerGameLinkRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public GamerGameLinkControllerIntegrationTest(GamerRepository gamerRepository, GameRepository gameRepository, MockMvc mockMvc, GamerGameLinkRepository gamerGameLinkRepository) {
        this.gamerRepository = gamerRepository;
        this.gameRepository = gameRepository;
        this.gamerGameLinkRepository = gamerGameLinkRepository;
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
        GamerGameLinkEntity gamerGameLink = new GamerGameLinkEntity(gamerGameId, Level.N00B);

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
        GamerGameLinkEntity gamerGameLink = new GamerGameLinkEntity(gamerGameId, Level.N00B);

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
        GamerGameLinkEntity gamerGameLink = new GamerGameLinkEntity(gamerGameId, Level.N00B);

        String gamerGameLinkJson = objectMapper.writeValueAsString(gamerGameLink);

        mockMvc.perform(
                post("/gamer-game-links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gamerGameLinkJson)
                ).andExpect(status().isBadRequest())
                .andExpect(content().string("Game with ID 100 does not exist."));
    }

    @Test
    public void testThatMatchingGamersReturns200AndCorrectData() throws Exception {
        //Save games to the database, A and B are from the same country
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();
        GamerEntity gamerEntityB = TestUtil.getGamerEntityB();
        GamerEntity gamerEntityC = TestUtil.getGamerEntityC();

        gamerEntityA.setCountry("Denmark");
        gamerEntityB.setCountry("Denmark");
        gamerEntityC.setCountry("Sweden");

        gamerRepository.save(gamerEntityA);
        gamerRepository.save(gamerEntityB);
        gamerRepository.save(gamerEntityC);

        //Add the games to the database
        GameEntity gameEntityA = TestUtil.getGameEntityA();
        GameEntity gameEntityB = TestUtil.getGameEntityB();

        gameRepository.save(gameEntityA);
        gameRepository.save(gameEntityB);

        //Link gamers to games
        //Gamer A plays game A at Pro level
        //Gamer B plays game A at pro level
        //Gamer C plays game A at noob level and Game B and pro level
        Long gamerIdA = gamerEntityA.getId();
        Long gamerIdB = gamerEntityB.getId();
        Long gamerIdC = gamerEntityC.getId();

        Long gameIdA = gameEntityA.getId();
        Long gameIdB = gameEntityB.getId();

        GamerGameId gamerAGameAId = new GamerGameId(gameIdA, gamerIdA);
        GamerGameId gamerBGameAId = new GamerGameId(gameIdA, gamerIdB);
        GamerGameId gamerCGameBId = new GamerGameId(gameIdB, gamerIdC);
        GamerGameId gamerCGameAId = new GamerGameId(gameIdA, gamerIdC);

        GamerGameLinkEntity gamerGameLinkAAPRO = new GamerGameLinkEntity(gamerAGameAId, Level.PRO);
        GamerGameLinkEntity gamerGameLinkBAPRO = new GamerGameLinkEntity(gamerBGameAId, Level.PRO);
        GamerGameLinkEntity gamerGameLinkCAPNOOB = new GamerGameLinkEntity(gamerCGameAId, Level.N00B);
        GamerGameLinkEntity gamerGameLinkCBPRO = new GamerGameLinkEntity(gamerCGameBId, Level.PRO);

        gamerGameLinkRepository.save(gamerGameLinkAAPRO);
        gamerGameLinkRepository.save(gamerGameLinkBAPRO);
        gamerGameLinkRepository.save(gamerGameLinkCAPNOOB);
        gamerGameLinkRepository.save(gamerGameLinkCBPRO);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/gamer-game-links/match")
                        .param("country", "Denmark")
                        .param("gameId", String.valueOf(gameEntityA.getId()))
                        .param("level", "PRO"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].country").value("Denmark"))
                .andExpect(jsonPath("$[1].country").value("Denmark"));
    }
}