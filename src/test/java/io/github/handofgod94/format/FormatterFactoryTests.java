package io.github.handofgod94.format;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;

/**
 * FormatterFactoryTests
 */
public class FormatterFactoryTests {

  @Test
  void createFormatter_returns_JpegFormatter_when_fileExt_is_jpg(){
    Formatter formatter = FormatterFactory.createFormatter("jpg");
    assertTrue(formatter instanceof JpegFormatter);
  }

  @Test
  void createFormatter_returns_PngFormatter_when_fileExt_is_png() {
    Formatter formatter = FormatterFactory.createFormatter("png");
    assertTrue(formatter instanceof PngFormatter);
  }

  @Test
  void createFormatter_throws_NotImplementedException_when_fileExt_is_invalid() {
    assertThrows(NotImplementedException.class, () -> {
      FormatterFactory.createFormatter("dummy");
    });
  }

}