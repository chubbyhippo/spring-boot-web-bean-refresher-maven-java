package io.github.chubbyhippo.refresher;

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
    public String getMessage() {
        return messageService.getMessage();
    }

    @PostMapping("/message")
    public void setMessage(@RequestBody String message) {
        messageService.setMessage(message);
    }

}
