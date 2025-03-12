package io.github.chubbyhippo.refresher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeanController {
    @GetMapping("/beans")
    List<String> getBeans() {
        return List.of("messageService", "messageController");
    }
}
