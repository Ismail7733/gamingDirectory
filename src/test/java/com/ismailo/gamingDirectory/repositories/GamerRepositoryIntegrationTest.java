package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GamerRepositoryIntegrationTest {

    private GamerRepository gamerRepository;

    @Autowired
    public GamerRepositoryIntegrationTest(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Test
    public void testThatYouCanAddGamersAndFindThem() {
        GamerEntity gamerEntityA = getGamerEntityA();
        gamerRepository.save(gamerEntityA);
        GamerEntity gamerEntityB = getGamerEntityB();
        gamerRepository.save(gamerEntityB);
        GamerEntity gamerEntityC = getGamerEntityC();
        gamerRepository.save(gamerEntityC);

        List<GamerEntity> result = gamerRepository.findAll();

        assertThat(result.size(), is(3));

        assertThat(result, containsInAnyOrder(gamerEntityA, gamerEntityB, gamerEntityC));
    }

    private GamerEntity getGamerEntityA() {
        return new GamerEntity("Denmark");
    }
    private GamerEntity getGamerEntityB() {
        return new GamerEntity("Denmark");
    }
    private GamerEntity getGamerEntityC() {
        return new GamerEntity("Sweden");
    }
}
