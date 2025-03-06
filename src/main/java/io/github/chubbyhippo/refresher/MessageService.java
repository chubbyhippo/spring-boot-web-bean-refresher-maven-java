package io.github.chubbyhippo.refresher;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private String message;

    public MessageService() {
        this.message = "Default message";
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
