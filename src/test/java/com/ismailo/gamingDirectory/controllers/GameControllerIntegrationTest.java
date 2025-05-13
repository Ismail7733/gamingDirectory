package com.ismailo.gamingDirectory.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ismailo.gamingDirectory.TestUtil;
import com.ismailo.gamingDirectory.entities.GameEntity;
import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.services.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {

    private GameService gameService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public GameControllerIntegrationTest(GameService gameService, MockMvc mockMvc) {
        this.gameService = gameService;
        this.mockMvc = mockMvc;
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatGameSuccessfullyReturnsHttp201Created() throws Exception {
        GameEntity gameEntityA = TestUtil.getGameEntityA();

        String gameJson = objectMapper.writeValueAsString(gameEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateGamerSuccessfullyReturnsSavedGamer() throws Exception {
        GameEntity gameEntityA = TestUtil.getGameEntityA();

        String gameJson = objectMapper.writeValueAsString(gameEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gameJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Overwatch")
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatinvalidGameFails() throws Exception {
        GamerEntity invalidGame = TestUtil.getGamerEntityB();

        String invalidGameJson = objectMapper.writeValueAsString(invalidGame);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidGameJson)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testThatGamerCanBeFoundAfterBeingCreated() throws Exception {
        GameEntity gameEntityA = TestUtil.getGameEntityA();

        gameService.createGame(gameEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/games/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Overwatch")
        );
    }


}
