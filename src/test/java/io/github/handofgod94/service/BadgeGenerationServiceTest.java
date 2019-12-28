package io.github.handofgod94.service;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Success;
import static org.junit.jupiter.api.Assertions.*;

class BadgeGenerationServiceTest {

  BadgeGenerationService service;
  File jacocoReportFile;
  File outputFile;
  MyMojoConfiguration.Builder configBuilder;


  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    jacocoReportFile = Paths.get(getClass().getClassLoader().getResource("jacoco.csv").toURI()).toFile();
    outputFile = Files.createTempFile("temp",".svg").toFile();
    configBuilder = MyMojoConfiguration.builder()
      .setCoverageCategory(Badge.CoverageCategory.INSTRUCTION)
      .setBadgeLabel("foobarfizzbuzz")
      .setJacocoReportFile(jacocoReportFile)
      .setOutputFile(outputFile);
    service = new BadgeGenerationService(configBuilder.build());
  }

  @Test
  void execute() {
    Option<Badge> badge = service.generate();
    assertFalse(badge.isEmpty());
  }

  @Test
  void generateBadgeString_WhenValidBadgeIsProvided_ItGeneratesSvgStringWithFilledValues() {
    Badge badge = service.generate().get();

    String badgeString = service.generateBadgeString();

    assertTrue(badgeString.contains("foobarfizzbuzz"));
  }
}
