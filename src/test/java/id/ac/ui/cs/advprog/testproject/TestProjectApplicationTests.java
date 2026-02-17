package id.ac.ui.cs.advprog.testproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class TestProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainShouldRunWithoutException() {
        assertDoesNotThrow(() -> TestProjectApplication.main(new String[]{
                "--spring.main.web-application-type=none",
                "--spring.main.banner-mode=off"
        }));
    }

}
