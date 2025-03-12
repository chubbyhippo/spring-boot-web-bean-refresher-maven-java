package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeanServiceTest {

    @InjectMocks
    private BeanService beanService;
    @Mock
    private ApplicationContext context;

    @Test
    @DisplayName("should return name of beans")
    void shouldReturnNameOfBeans() {
        when(context.getBeanDefinitionNames())
                .thenReturn(new String[]{"beanService"});
        assertThat(beanService.getBeans()).contains("beanService");

        verify(context, times(1)).getBeanDefinitionNames();
    }

}