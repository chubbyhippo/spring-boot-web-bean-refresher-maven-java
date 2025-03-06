package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MessageService.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    @DisplayName("should return default message")
    @Order(1)
    void shouldReturnDefaultMessage() {
        String result = messageService.getMessage();
        assertThat(result).isEqualTo("Default message");
    }

    @Test
    @DisplayName("should return changed message")
    @Order(2)
    void shouldReturnChangedMessage() {
        messageService.setMessage("Changed message");
        String result = messageService.getMessage();
        assertThat(result).isEqualTo("Changed message");
    }

}