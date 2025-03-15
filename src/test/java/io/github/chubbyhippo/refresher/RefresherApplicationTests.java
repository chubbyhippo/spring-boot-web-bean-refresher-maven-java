package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class RefresherApplicationTests {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    @DisplayName("should return message")
    @Order(1)
    void shouldReturnMessage() {
        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Default message");
    }

    @Test
    @DisplayName("should return changed message")
    @Order(2)
    void shouldReturnChangedMessage() {
        mockMvcTester.post()
                .uri("/message")
                .content("Changed message")
                .assertThat()
                .hasStatusOk();

        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Changed message");
    }

    @Test
    @DisplayName("should return bean ahs been refreshed message")
    @Order(3)
    void shouldReturnBeanAhsBeenRefreshedMessage() {
        mockMvcTester.post()
                .uri("/refresh")
                .assertThat()
                .hasStatusOk();
    }

    @Test
    @DisplayName("should trigger refresh scope")
    void shouldTriggerRefreshScope() {

        mockMvcTester.post()
                .uri("/message")
                .content("Changed message")
                .assertThat()
                .hasStatusOk();

        mockMvcTester.post()
                .uri("/refresh")
                .exchange();

        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Default message");
    }


    @Test
    @DisplayName("should return list of classes")
    void shouldReturnListOfClasses() {
        mockMvcTester.get()
                .uri("/classes")
                .assertThat()
                .hasStatusOk()
                .bodyText()
                .contains("ClassController");

    }
}
