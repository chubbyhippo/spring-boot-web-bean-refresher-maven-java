package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.mockito.Mockito.*;

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