package io.github.chubbyhippo.refresher.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageServiceTest {

    @Test
    @DisplayName("should return default message")
    void shouldReturnDefaultMessage() {
        var messageService = new MessageService();
        var message = messageService.getMessage();
        assertThat(message).isEqualTo("Default message");
    }

    @Test
    @DisplayName("should return set message")
    void shouldReturnSetMessage() {
        var messageService = new MessageService();
        messageService.setMessage("Set message");
        assertThat(messageService.getMessage()).isEqualTo("Set message");
    }

}