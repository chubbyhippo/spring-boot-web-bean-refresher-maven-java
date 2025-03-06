package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MessageService.class)
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    @DisplayName("should return default message")
    void shouldReturnDefaultMessage() {
        String result = messageService.getMessage();
        assertThat(result).isEqualTo("Default message");
    }

}