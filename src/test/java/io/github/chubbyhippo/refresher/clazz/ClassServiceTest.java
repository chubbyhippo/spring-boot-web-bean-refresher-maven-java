package io.github.chubbyhippo.refresher.clazz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClassServiceTest {

    @Spy
    private ClassService classService;

    @Test
    @DisplayName("should return fully qualify name for classes in the project")
    void shouldReturnFullyQualifyNameForClassesInTheProject() {
        List<String> classes = classService.getClasses();
        assertThat(classes).isNotEmpty();
    }

    @Test
    @DisplayName("should get fully qualify name by search word")
    void shouldGetFullyQualifyNameBySearchWord() {
        String fullyQualifiedName = classService.getFullyQualifiedNameBy("messageservice");
        assertThat(fullyQualifiedName)
                .isEqualTo("io.github.chubbyhippo.refresher.message.MessageService");

    }

    @Test
    @DisplayName("should get empty string if not found")
    void shouldGetEmptyStringIfNotFound() {
        String input = "notfound";
        String fullyQualifiedName = classService.getFullyQualifiedNameBy(input);
        assertThat(fullyQualifiedName)
                .isEqualTo("No class found for: " + input);
    }

}