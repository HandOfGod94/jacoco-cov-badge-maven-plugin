package io.github.handofgod94.generator;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

class BaseBadgeGeneratorTest {

  File jacocoReport;
  BaseBadgeGenerator base;

  @BeforeEach
  void setup() throws URISyntaxException {
    jacocoReport = new File(getClass().getClassLoader().getResource("jacoco.csv").toURI());
    base = new BaseBadgeGenerator();
  }

  @Test
  void initializeConfiguration_loads_freemarker_configuration() {
    Configuration actual = base.initializeConfiguration();

    Assert.assertEquals("UTF-8", actual.getDefaultEncoding());
  }

  @Test
  void calculateCoverage_calculates_coverage_for_the_badge() {
    Coverage actual = base.calculateCoverage(jacocoReport, Badge.CoverageCategory.INSTRUCTION);
    Coverage expected = new Coverage( 528L,  171L, Badge.CoverageCategory.INSTRUCTION);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void initializeBadge_generates_Badge_with_required_info() {
    Coverage mockCoverage = Mockito.mock(Coverage.class);
    Mockito.when(mockCoverage.getCoveragePercentage()).thenReturn(45.0f);

    Badge actual = base.initializeBadge(mockCoverage, "foo");
    Badge execpted = new Badge("foo", 45);

    Assertions.assertEquals(execpted, actual);
  }

  @Test
  void renderBadgeString_creates_badge_svg_String_with_filled_values() {
    Configuration configuration = base.initializeConfiguration();
    Badge badge = new Badge("foobarvalue", 55);

    String badgeString = base.renderBadgeString(configuration, badge);

    Assertions.assertTrue(badgeString.contains("foobarvalue"));
  }

  @Test
  void saveToFile_returns_true_for_successful_save() throws IOException {
    File outputTempFile = Files.createTempFile("temp",".svg").toFile();
    String dummyRenderedString = "dummy";

    boolean isSaveSuccess = base.saveToFile(outputTempFile, dummyRenderedString);

    Assertions.assertTrue(isSaveSuccess);
  }
}