package io.github.handofgod94.service;

import io.github.handofgod94.domain.Badge;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BadgeGenerationServiceTest {

  BadgeGenerationService service;
  File jacocoReportFile;
  File outputFile;


  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    jacocoReportFile = Paths.get(getClass().getClassLoader().getResource("jacoco.csv").toURI()).toFile();
    outputFile = Files.createTempFile("temp",".svg").toFile();
    service = new BadgeGenerationService(Badge.CoverageCategory.INSTRUCTION, "foo", jacocoReportFile, outputFile);
  }

  @Test
  void execute() {
    Option<Badge> badge = service.generate();
    assertFalse(badge.isEmpty());
  }
}
