package io.github.chubbyhippo.refresher.clazz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(controllers = ClassController.class)
public class ClassControllerTest {
   @Autowired
   private MockMvcTester mockMvcTester;

    @Test
    @DisplayName("should return list of classes")
    void shouldReturnListOfClasses() {
       mockMvcTester.get()
               .uri("/classes")
               .assertThat()
               .hasStatusOk();

    }
}
