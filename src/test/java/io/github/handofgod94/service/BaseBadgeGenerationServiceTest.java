package io.github.handofgod94.service;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.coverage.Coverage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URISyntaxException;

class BaseBadgeGenerationServiceTest {

  File jacocoReport;
  BaseBadgeGenerationService base;

  @BeforeEach
  void setup() throws URISyntaxException {
    jacocoReport = new File(getClass().getClassLoader().getResource("jacoco.csv").toURI());
    base = new BaseBadgeGenerationService();
  }

  @Test
  void calculateCoverage_calculates_coverage_for_the_badge() {
    Coverage actual = base.calculateCoverage(jacocoReport, Coverage.CoverageCategory.INSTRUCTION);

    long expectedCovered = 113L;

    Assertions.assertEquals(expectedCovered, actual.calculateCovered());
  }

  @Test
  void initializeBadge_generates_Badge_with_required_info() {
    Coverage mockCoverage = Mockito.mock(Coverage.class);
    Mockito.when(mockCoverage.getCoveragePercentage()).thenReturn(45.0f);

    Badge actual = base.initializeBadge(mockCoverage, "foo");
    Badge execpted = Badge.create("foo", 45);

    Assertions.assertEquals(execpted, actual);
  }

//  @Test
//  void saveToFile_returns_true_for_successful_save() throws IOException {
//    File outputTempFile = Files.createTempFile("temp",".svg").toFile();
//    String dummyRenderedString = "dummy";
//
//    boolean isSaveSuccess = base.saveToFile(outputTempFile, dummyRenderedString).isSuccess();
//
//    Assertions.assertTrue(isSaveSuccess);
//  }
}
