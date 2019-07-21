package io.github.handofgod94.parser;

import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.github.handofgod94.domain.ReportLineBuilder;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CsvReportParserTests {

  @Test
  void parseReport_should_generate_report_if_report_is_valid() throws IOException {
    String csvString =
      "GROUP,PACKAGE,CLASS,INSTRUCTION_MISSED,INSTRUCTION_COVERED,BRANCH_MISSED,BRANCH_COVERED,LINE_MISSED,LINE_COVERED,COMPLEXITY_MISSED,COMPLEXITY_COVERED,METHOD_MISSED,METHOD_COVERED\n"
      + "io.github.handofgod94:jacoco-cov-badge-maven-plugin,io.github.handofgod94,MyMojo,13,113,0,0,4,24,0,3,0,3\n";
    BufferedReader reader = new BufferedReader(new StringReader(csvString));
    ReportParser reportParser = new CsvReportParser();
    Report actualReport = reportParser.parseReport(reader);
    ReportLine expectedReportLine = new ReportLineBuilder()
      .addGroupName("io.github.handofgod94:jacoco-cov-badge-maven-plugin")
      .addPackageName("io.github.handofgod94")
      .addClassName("MyMojo")
      .addInstructionMissed(13)
      .addInstructionCovered(113)
      .addLineMissed(4)
      .addLineCovered(24)
      .addComplexityCovered(3)
      .addMethodCovered(3).build();

    assertAll("should have correct entities in report",
      () -> assertFalse("it should have ReportLine" , actualReport.getLines().isEmpty()),
      () -> assertTrue("it should contain expected ReportLine",actualReport.getLines().contains(expectedReportLine)));
  }
}
