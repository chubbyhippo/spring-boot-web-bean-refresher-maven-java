package io.github.chubbyhippo.refresher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

@WebMvcTest(BeanController.class)
class BeanControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("should return bean name list")
    void shouldReturnBeanNameList() throws JsonProcessingException {
        mockMvcTester.get()
                .uri("/beans")
                .assertThat()
                .hasStatusOk()
                .hasBodyTextEqualTo(objectMapper.writeValueAsString(List.of("messageService",
                                "messageController")));
    }

}