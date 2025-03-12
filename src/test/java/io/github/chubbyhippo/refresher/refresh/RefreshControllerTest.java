package io.github.chubbyhippo.refresher.refresh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = RefreshController.class)
public class RefreshControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;
    @MockitoBean
    private RefreshService refreshService;

    @Test
    @DisplayName("should run refresh service trigger refresh")
    void shouldRunRefreshServiceTriggerRefresh() {
        doNothing().when(refreshService)
                .triggerRefresh();
        mockMvcTester.post()
                .uri("/refresh")
                .exchange();
        verify(refreshService, times(1))
                .triggerRefresh();
    }
}
