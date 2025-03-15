package io.github.chubbyhippo.refresher.refresh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RefreshTest {
    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    @DisplayName("should return bean has been refreshed message")
    void shouldReturnBeanHasBeenRefreshedMessage() {
        mockMvcTester.post()
                .uri("/refresh")
                .assertThat()
                .hasStatusOk();
    }
}
