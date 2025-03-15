package io.github.chubbyhippo.refresher.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BeanTest {

    @Autowired
    private MockMvcTester mockMvcTester;
    @Autowired
    private ObjectMapper objectMapper;

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
