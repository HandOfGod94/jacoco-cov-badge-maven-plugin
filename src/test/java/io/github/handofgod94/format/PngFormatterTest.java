package io.github.handofgod94.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PngFormatterTest {

  private Formatter formatter;

  @BeforeEach
  void setup() {
    formatter = new PngFormatter();
  }

  @Test
  void save_creates_file_given_an_svg_text_provide() throws IOException {
    File file = Files.createTempFile("temp",".jpg").toFile();

    boolean isSuccess = formatter.save(file, "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
      "  width=\"94.688\" height=\"20\"></svg>").isSuccess();
    byte[] lines = Files.readAllBytes(Paths.get(file.toURI()));

    assertTrue(isSuccess);
    assertTrue(lines.length > 0);
  }

  @Test
  void save_should_not_create_file_for_invalid_svg_text() throws IOException {
    File file = Files.createTempFile("temp",".txt").toFile();

    boolean isFailure = formatter.save(file, "text").isFailure();

    assertTrue(isFailure);
  }
}
