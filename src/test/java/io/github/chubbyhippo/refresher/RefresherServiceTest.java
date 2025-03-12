package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RefresherServiceTest {
    @InjectMocks
    private RefresherService refresherService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    @DisplayName("should publish refresh scope refreshed event")
    void shouldPublishRefreshScopeRefreshedEvent() {
        doNothing().when(eventPublisher)
                .publishEvent(any(RefreshScopeRefreshedEvent.class));
        refresherService.triggerRefresh();
        verify(eventPublisher, times(1))
                .publishEvent(any(RefreshScopeRefreshedEvent.class));
    }


}
