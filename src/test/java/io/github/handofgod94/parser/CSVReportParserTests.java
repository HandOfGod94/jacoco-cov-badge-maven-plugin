package io.github.handofgod94.parser;

import io.github.handofgod94.domain.ReportLine;
import io.github.handofgod94.domain.ReportLineBuilder;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CSVReportParserTests {

  @Test
  void parseReport_should_generate_report_if_report_is_valid() throws IOException {
    String csvString =
      "GROUP,PACKAGE,CLASS,INSTRUCTION_MISSED,INSTRUCTION_COVERED,BRANCH_MISSED,BRANCH_COVERED,LINE_MISSED,LINE_COVERED,COMPLEXITY_MISSED,COMPLEXITY_COVERED,METHOD_MISSED,METHOD_COVERED\n"
      + "io.github.handofgod94:jacoco-cov-badge-maven-plugin,io.github.handofgod94,MyMojo,13,113,0,0,4,24,0,3,0,3\n";
    BufferedReader reader = new BufferedReader(new StringReader(csvString));
    ReportParser reportParser = new CSVReportParser();
    List<ReportLine> actualReport = reportParser.parseReport(reader);
    ReportLine expectedReportLine = new ReportLineBuilder()
      .addGroup("io.github.handofgod94:jacoco-cov-badge-maven-plugin")
      .addPackage("io.github.handofgod94")
      .addClass("MyMojo")
      .addJInstructionMissed(13)
      .addJInstructionCovered(113)
      .addJLineMissed(4)
      .addJLineCovered(24)
      .addJComplexityCovered(3)
      .addJMethodCovered(3).build();

    assertAll("should have correct entities in report",
      () -> assertFalse("it should have ReportLine" , actualReport.isEmpty()),
      () -> assertTrue("it should contain expected ReportLine",actualReport.contains(expectedReportLine)));
  }
}
