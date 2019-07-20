package io.github.handofgod94.generator;

import io.github.handofgod94.domain.Badge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BadgeGeneratorTest {

  BadgeGenerator badgeGenerator;
  File jacocoReportFile;
  File outputFile;


  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    jacocoReportFile = Paths.get(getClass().getClassLoader().getResource("jacoco.csv").toURI()).toFile();
    outputFile = Files.createTempFile("temp",".svg").toFile();
    badgeGenerator = new BadgeGenerator(Badge.CoverageCategory.INSTRUCTION, "foo", jacocoReportFile, outputFile);
  }

  @Test
  void execute() {
    Badge badge = badgeGenerator.execute();
    assertNotNull(badge);
  }
}
