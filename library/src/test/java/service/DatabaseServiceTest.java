package service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("Test database library")
public class DatabaseServiceTest {
    private static final String CLIENT_SEQUENCE = "clientid";

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void contextLoads() {
        //assertThat(databaseService.getNextSequence(CLIENT_SEQUENCE)).isNotNull();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}