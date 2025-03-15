package io.github.chubbyhippo.refresher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RefresherApplicationTests {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Test
    @DisplayName("should trigger refresh scope")
    void shouldTriggerRefreshScope() {

        mockMvcTester.post()
                .uri("/message")
                .content("Changed message")
                .assertThat()
                .hasStatusOk();

        mockMvcTester.post()
                .uri("/refresh")
                .exchange();

        mockMvcTester.get()
                .uri("/message")
                .assertThat()
                .hasBodyTextEqualTo("Default message");
    }

}
