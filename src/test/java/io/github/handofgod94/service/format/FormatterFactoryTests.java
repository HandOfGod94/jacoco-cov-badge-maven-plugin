package io.github.handofgod94.service.format;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  void createFormatter_returns_PngFormatter_when_fileExt_is_svg() {
    Formatter formatter = FormatterFactory.createFormatter("svg");
    assertTrue(formatter instanceof SvgFormatter);
  }

  @Test
  void createFormatter_throws_NotImplementedException_when_fileExt_is_invalid() {
    assertThrows(UnsupportedOperationException.class, () -> {
      FormatterFactory.createFormatter("dummy");
    });
  }

}
