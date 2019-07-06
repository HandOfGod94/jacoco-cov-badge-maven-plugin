package io.github.handofgod94.generator;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
