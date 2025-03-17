package io.github.chubbyhippo.refresher.refresh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.endpoint.RefreshEndpoint;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RefreshServiceTest {
    @InjectMocks
    private RefreshService refreshService;


    @Mock
    private RefreshEndpoint refreshEndpoint;

    @Test
    @DisplayName("should publish refresh scope refreshed event")
    void shouldPublishRefreshScopeRefreshedEvent() {
        when(refreshEndpoint.refresh()).thenReturn(null);
        refreshService.triggerRefresh();
        verify(refreshEndpoint, times(1))
                .refresh();
    }
}
