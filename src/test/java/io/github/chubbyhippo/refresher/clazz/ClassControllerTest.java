package io.github.chubbyhippo.refresher.clazz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ClassController.class)
public class ClassControllerTest {
    @Autowired
    private MockMvcTester mockMvcTester;
    @MockitoBean
    private ClassService classService;

    @Test
    @DisplayName("should return list of classes")
    void shouldReturnListOfClasses() {
        when(classService.getClasses()).thenReturn(List.of(
                "io.github.chubbyhippo.refresher.refresh.RefreshService",
                "io.github.chubbyhippo.refresher.refresh.RefreshController",
                "io.github.chubbyhippo.refresher.bean.BeanService",
                "io.github.chubbyhippo.refresher.bean.BeanController",
                "io.github.chubbyhippo.refresher.message.MessageController",
                "io.github.chubbyhippo.refresher.message.MessageService",
                "io.github.chubbyhippo.refresher.clazz.ClassController",
                "io.github.chubbyhippo.refresher.RefresherApplication"
        ));
        mockMvcTester.get()
                .uri("/classes")
                .assertThat()
                .hasStatusOk()
                .bodyText()
                .contains("ClassController");

    }
}
