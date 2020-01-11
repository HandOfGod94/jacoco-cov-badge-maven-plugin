package io.github.handofgod94.service;

import com.google.common.io.Resources;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.coverage.CoverageCategory;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.vavr.control.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BadgeGeneratorTest {

  BadgeGenerator generator;
  File jacocoReportFile;
  File outputFile;
  MyMojoConfiguration.Builder configBuilder;


  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    jacocoReportFile = new File(Resources.getResource("jacoco.csv").getFile());
    outputFile = Files.createTempFile("temp", ".svg").toFile();
    configBuilder = MyMojoConfiguration.builder()
      .setCoverageCategory(CoverageCategory.INSTRUCTION)
      .setBadgeLabel("foobarfizzbuzz")
      .setJacocoReportFile(jacocoReportFile)
      .setOutputFile(outputFile);
    generator = new BadgeGenerator(configBuilder.build());
  }

  @Test
  void generate_WhenConfigIsValid_ItGeneratesBadge() {
    Option<Badge> badge = generator.generate();
    assertTrue(badge.isDefined());
    assertEquals(89L, badge.get().getBadgeValue());
  }
}
