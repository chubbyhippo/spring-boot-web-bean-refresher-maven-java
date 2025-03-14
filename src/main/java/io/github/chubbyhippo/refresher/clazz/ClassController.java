package io.github.chubbyhippo.refresher.clazz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassController {

    @GetMapping("/classes")
    List<String> getClasses() {
       return List.of();
    }
}
