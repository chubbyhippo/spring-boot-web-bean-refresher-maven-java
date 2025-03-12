package io.github.chubbyhippo.refresher.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BeanService {
    private final ApplicationContext context;

    public BeanService(ApplicationContext context) {
        this.context = context;
    }

    public List<String> getBeans() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        return Arrays.stream(beanDefinitionNames).toList();
    }
}
