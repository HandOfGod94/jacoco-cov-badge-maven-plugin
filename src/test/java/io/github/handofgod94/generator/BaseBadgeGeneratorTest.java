package io.github.handofgod94.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class BaseBadgeGeneratorTest {

  private BaseBadgeGenerator baseBadgeGenerator;
  private File jacocoReport;

  @BeforeEach
  void setup() throws URISyntaxException {
    baseBadgeGenerator = new BaseBadgeGenerator();
    jacocoReport = new File(getClass().getClassLoader().getResource("jacoco.csv").toURI());
  }

  @Test
  void loadFreemarkerConfig_loads_config_for_freemarker() {
    // just sanity to verify config api is woring.
    baseBadgeGenerator.loadFreemarkerConfig();
    Configuration config = baseBadgeGenerator.getConfiguration();
    assertEquals(config.getDefaultEncoding(), "UTF-8");
  }

  @Test
  void calculateCoverage_loads_Coverage() throws IOException {
    baseBadgeGenerator.calculateCoverage(jacocoReport, Badge.CoverageCategory.INSTRUCTION);
    Coverage expected = new Coverage( 528L,  171L, Badge.CoverageCategory.INSTRUCTION);
    Coverage actual = baseBadgeGenerator.getCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void generateBadgeData_initializes_badge() throws IOException {
    Coverage mockCoverage = Mockito.mock(Coverage.class);
    Mockito.when(mockCoverage.getCoveragePercentage()).thenReturn(30.0f);

    baseBadgeGenerator.setCoverage(mockCoverage);
    baseBadgeGenerator.generateBadgeData("foo");
    Badge expected = new Badge("foo", 30);
    Badge actual = baseBadgeGenerator.getBadge();

    assertEquals(expected, actual);
  }

  @Test
  void processBadgeTemplate_returns_writer_with_badge_values() throws TemplateException, IOException, TranscoderException {
    Badge badge = new Badge("foo", 10);

    baseBadgeGenerator.setBadge(badge);
    baseBadgeGenerator.loadFreemarkerConfig();
    baseBadgeGenerator.processSvgBadgeTemplate();
    String svgString = baseBadgeGenerator.getProcessedTemplateString();

    assertTrue(svgString.contains("foo"));
  }
}
