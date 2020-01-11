package io.github.handofgod94.service.parser;

import com.google.common.io.Resources;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CsvReportParserTests {

  @Test
  void parseReport_WhenReportIsValid_ItGeneratesReport() throws IOException, URISyntaxException {
    File file = new File(Resources.getResource("jacoco-single-line.csv").getFile());
    CsvReportParser reportParser = new CsvReportParser();
    Report actualReport = reportParser.parseReport(file);

    ReportLine expectedReportLine = ReportLine.builder()
      .setGroupName("io.github.handofgod94:jacoco-cov-badge-maven-plugin")
      .setPackageName("io.github.handofgod94")
      .setClassName("MyMojo")
      .setInstructionMissed(13)
      .setInstructionCovered(113)
      .setLineMissed(4)
      .setLineCovered(24)
      .setComplexityCovered(3)
      .setMethodCovered(3).build();

    assertAll("when report is valid",
      () -> assertTrue("it contains expected report line", actualReport.getLines().contains(expectedReportLine)));
  }
}
