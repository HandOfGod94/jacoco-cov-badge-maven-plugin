package io.github.handofgod94;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class BadgeUtilityTests {
  @Test
  void getFileExt_returns_extension_wrapped_in_optional_for_valid_file() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("filename.txt");
    String ext = BadgeUtility.getFileExt(file).get();
    assertEquals("txt", ext);
  }

  @Test
  void getFileExt_returns_empty_for_invalid_file() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("filename");
    Optional<String> optExt = BadgeUtility.getFileExt(file);
    assertEquals(Optional.empty(), optExt);
  }
}
