package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {BeanRefresherService.class, MessageService.class})
class BeanRefresherServiceTest {

    @Autowired
    private BeanRefresherService beanRefresherService;

    @Test
    @DisplayName("should call getBean")
    void shouldCallGetBean() {
        var messageService = "messageService";
        var messageServiceBean = beanRefresherService.refreshBean(messageService);
        System.out.println(messageServiceBean.getClass().getName());
        assertThat(messageServiceBean).isNotNull();
    }

}