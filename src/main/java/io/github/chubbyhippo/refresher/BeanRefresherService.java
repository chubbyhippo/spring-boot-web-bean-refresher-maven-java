package io.github.chubbyhippo.refresher;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BeanRefresherService {
    private final ApplicationContext applicationContext;

    public BeanRefresherService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object refreshBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
