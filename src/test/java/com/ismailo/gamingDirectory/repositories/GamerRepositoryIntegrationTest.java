package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.TestUtil;
import com.ismailo.gamingDirectory.entities.GamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;


import java.util.List;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EntityScan(basePackages = "com.ismailo.gamingDirectory.entities")
public class GamerRepositoryIntegrationTest {

    private GamerRepository gamerRepository;

    @Autowired
    public GamerRepositoryIntegrationTest(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Test
    public void testThatYouCanAddGamersAndFindThem() {
        GamerEntity gamerEntityA = TestUtil.getGamerEntityA();
        gamerRepository.save(gamerEntityA);
        GamerEntity gamerEntityB = TestUtil.getGamerEntityB();
        gamerRepository.save(gamerEntityB);
        GamerEntity gamerEntityC = TestUtil.getGamerEntityC();
        gamerRepository.save(gamerEntityC);

        List<GamerEntity> result = gamerRepository.findAll();

        assertThat(result.size(), is(3));

        assertThat(result, containsInAnyOrder(gamerEntityA, gamerEntityB, gamerEntityC));
    }
}
