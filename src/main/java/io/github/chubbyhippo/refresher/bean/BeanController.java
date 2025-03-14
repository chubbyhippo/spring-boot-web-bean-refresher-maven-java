package io.github.chubbyhippo.refresher.bean;

import io.github.chubbyhippo.refresher.message.MessageService;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeanController {
    private final BeanService beanService;
    private final ConfigurableApplicationContext context;

    public BeanController(BeanService beanService, ConfigurableApplicationContext context) {
        this.beanService = beanService;
        this.context = context;
    }

    @GetMapping("/beans")
    List<String> getBeans() {
        return beanService.getBeans();
    }

    @PostMapping("/beanRestart")
    void restartBean(@RequestParam String beanName) {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) context.getBeanFactory();
        registry.destroySingleton(beanName);
        registry.registerSingleton(beanName, new MessageService());
    }
}
