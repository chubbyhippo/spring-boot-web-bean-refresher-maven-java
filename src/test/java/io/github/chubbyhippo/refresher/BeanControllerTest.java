package io.github.chubbyhippo.refresher;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(BeanController.class)
class BeanControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    @DisplayName("should return bean name list")
    void shouldReturnBeanNameList() throws JsonProcessingException {
        mockMvcTester.get()
                .uri("/beans")
                .assertThat()
                .hasStatusOk()
                .bodyText()
                .contains("messageService");
    }

}