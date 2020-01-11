package io.github.handofgod94.domain;

import com.google.common.io.Resources;
import io.github.handofgod94.domain.coverage.CoverageCategory;
import io.github.handofgod94.service.CsvReportParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportTest {

  File reportFile;

  @BeforeEach
  void setup() {
    URL url = Resources.getResource("jacoco-single-line.csv");
    reportFile = new File(url.getFile());
  }

  @Test
  void getCoverageValueFor_WhenCategoryIsPresentOnReport_ItReturnsCoveragePercentageValue() {
    Report report = new CsvReportParser(reportFile).parse();
    int actual = report.getCoverageValueFor(CoverageCategory.INSTRUCTION);

    assertEquals(89, actual);
  }

  @Test
  void getCoverageValueFor_WhenCategoryIsAbsentOnReport_ItReturnsCoveragePercentageValue() {
    Report report = new CsvReportParser(reportFile).parse();
    int actual = report.getCoverageValueFor(CoverageCategory.METHOD);

    assertEquals(0, actual);
  }
}
