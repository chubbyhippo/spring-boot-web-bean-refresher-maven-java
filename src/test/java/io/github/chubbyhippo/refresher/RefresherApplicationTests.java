package io.github.chubbyhippo.refresher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RefresherApplicationTests {

    @Autowired
    private MockMvcTester mockMvcTester;
    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("should return bean name list")
    void shouldReturnBeanNameList() throws UnsupportedEncodingException, JsonProcessingException {
        String contentAsString = mockMvcTester.get()
                .uri("/beans")
                .exchange()
                .getResponse()
                .getContentAsString();

        List<String> beans = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertThat(beans).isNotEmpty();
        beans.forEach(System.out::println);
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
    @DisplayName("should restart bean")
    void shouldRestartBean() {

        mockMvcTester.post()
                .uri("/message")
                .content("Changed message")
                .assertThat()
                .hasStatusOk();

        mockMvcTester.post()
                .uri("/beanRestart")
                .param("beanName", "messageService")
                .assertThat()
                .hasStatusOk();

        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Default message");

    }
}
