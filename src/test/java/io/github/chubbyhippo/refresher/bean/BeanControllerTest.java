package io.github.chubbyhippo.refresher.bean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(BeanController.class)
class BeanControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @MockitoBean
    private BeanService beanService;

    @Test
    @DisplayName("should return bean name list")
    void shouldReturnBeanNameList() {
        when(beanService.getBeans()).thenReturn(List.of("messageService"));

        mockMvcTester.get()
                .uri("/beans")
                .assertThat()
                .hasStatusOk()
                .bodyText()
                .contains("messageService");

        verify(beanService, times(1)).getBeans();
    }

}