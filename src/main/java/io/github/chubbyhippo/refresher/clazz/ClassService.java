package io.github.chubbyhippo.refresher.clazz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ClassService {
    private static final Logger log = LoggerFactory.getLogger(ClassService.class);
    public List<String> getClasses() {
        Path rootPath = Path.of("src/main/java");
        try (Stream<Path> stream = Files.walk(rootPath)) {
            return stream.filter(path -> path.toString().endsWith(".java"))
                    .map(path -> toFullyQualifiedName(rootPath, path))
                    .toList();
        } catch (IOException e) {
            log.error("Error: ", e);
        }
        return List.of();
    }

    private static String toFullyQualifiedName(Path rootPath, Path filePath) {
        Path relativePath = rootPath.relativize(filePath);
        String className = relativePath.toString()
                .replace("/", ".")
                .replace("\\", ".");
        return className.substring(0, className.length() - 5);
    }
}
