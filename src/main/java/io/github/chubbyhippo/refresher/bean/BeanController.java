package io.github.chubbyhippo.refresher.bean;

import io.github.chubbyhippo.refresher.clazz.ClassService;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class BeanController {
    private final BeanService beanService;
    private final ConfigurableApplicationContext context;
    private final ClassService classService;

    public BeanController(BeanService beanService, ConfigurableApplicationContext context, ClassService classService) {
        this.beanService = beanService;
        this.context = context;
        this.classService = classService;
    }

    @GetMapping("/beans")
    List<String> getBeans() {
        return beanService.getBeans();
    }

    @PostMapping("/beanRestart")
    void restartBean(@RequestParam String beanName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) context.getBeanFactory();
        registry.destroySingleton(beanName);

        String fullyQualifiedNameBy = classService.getFullyQualifiedNameBy(beanName);
        Class<?> clazz = Class.forName(fullyQualifiedNameBy);
        Object beanInstance = clazz.getDeclaredConstructor().newInstance();

        registry.registerSingleton(beanName, beanInstance);
    }
}
