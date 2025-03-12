package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Spy
    private MessageService messageService;

    @Test
    @DisplayName("should return default message")
    void shouldReturnDefaultMessage() {

        String message = messageService.getMessage();
        assertThat(message).isEqualTo("Default message");
    }

}