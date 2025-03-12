package io.github.chubbyhippo.refresher.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.mockito.Mockito.when;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;
    @MockitoBean
    private MessageService messageService;

    @Test
    @DisplayName("should return message")
    void shouldReturnMessage() {
        when(messageService.getMessage())
                .thenReturn("Default message");

        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Default message");
    }
}