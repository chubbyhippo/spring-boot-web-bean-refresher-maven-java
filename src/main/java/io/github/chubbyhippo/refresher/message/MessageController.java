package io.github.chubbyhippo.refresher.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    String getMessage() {
        return messageService.getMessage();
    }

    @PostMapping("/message")
    void setMessage(@RequestBody String message) {
        messageService.setMessage(message);
    }

    @PostMapping("/refresh")
    String refreshMessageService() {
       return "refresh";
    }
}
