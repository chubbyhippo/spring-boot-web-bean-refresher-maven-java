package io.github.chubbyhippo.refresher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeanController {
    private final BeanService beanService;

    public BeanController(BeanService beanService) {
        this.beanService = beanService;
    }

    @GetMapping("/beans")
    List<String> getBeans() {
        return beanService.getBeans();
    }
}
