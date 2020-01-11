package io.github.handofgod94.domain.coverage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.github.handofgod94.domain.CoverageCategory;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoverageTests {

  private Report report;
  private ReportLine line1;
  private ReportLine line2;
  private List<ReportLine> lines;

  @BeforeEach
  void setup() {
    report = mock(Report.class);
    line1 = mock(ReportLine.class);
    line2 = mock(ReportLine.class);
    lines = List.of(line1, line2);
    when(report.getLines()).thenReturn(lines);
  }

  @Test
  void getCoveragePercentage_returns_coverage_for_valid_input() {
    when(line1.getInstructionMissed()).thenReturn(50L);
    when(line1.getInstructionCovered()).thenReturn(50L);
    Coverage coverage = Coverage.create(CoverageCategory.INSTRUCTION, report);

    float actualPercentage = coverage.getCoveragePercentage();

    assertEquals(Float.valueOf(50.0f), actualPercentage);
  }

  @Test
  void getCoveragePercentage_returns_zero_for_invalid_input() {
    when(line1.getInstructionMissed()).thenReturn(-50L);
    when(line1.getInstructionCovered()).thenReturn(50L);
    Coverage coverage = Coverage.create(CoverageCategory.INSTRUCTION, report);
    CoverageCalculator calculator = mock(CoverageCalculator.class);

    float actualPercentage = coverage.getCoveragePercentage();

    assertEquals(0f, actualPercentage);
  }
}
