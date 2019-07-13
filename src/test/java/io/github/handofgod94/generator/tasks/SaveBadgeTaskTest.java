package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.generator.BadgeProcessState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class SaveBadgeTaskTest {

  private File outputFile;
  private BadgeProcessState mockState;

  @BeforeEach
  void setup() throws IOException {
    outputFile = Files.createTempFile("jacaco-badge",".svg").toFile();
    mockState = Mockito.mock(BadgeProcessState.class);
  }

  @Test
  void perform_generates_file() throws IOException {
    when(mockState.getRenderedString()).thenReturn("Mocked Value");
    when(mockState.getOutputFile()).thenReturn(outputFile);

    Task task = new SaveBadgeTask();
    task.perform(mockState);

    String outputFileContent = Files.readAllLines(Paths.get(outputFile.getAbsolutePath())).get(0);

    assertTrue(mockState.getOutputFile().exists());
    assertTrue(outputFileContent.contains("Mocked Value"));
  }
}
