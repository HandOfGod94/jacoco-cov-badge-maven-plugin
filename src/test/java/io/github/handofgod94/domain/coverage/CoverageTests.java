package io.github.handofgod94.domain.coverage;

import static org.junit.jupiter.api.Assertions.*;

import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CoverageTests {

  private Report report;
  private ReportLine line1;
  private ReportLine line2;
  private List<ReportLine> lines;

  @Test
  void getCoveragePercentage_returns_coverage_for_valid_input() {
    Coverage coverage = Coverage.create(10, 10, Coverage.CoverageCategory.INSTRUCTION);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(Float.valueOf(50.0f), actualPercentage);
  }

  @Test
  void getCoveragePercentage_returns_zero_for_invalid_input() {
    Coverage coverage = Coverage.create(-10, 10, Coverage.CoverageCategory.INSTRUCTION);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(0f, actualPercentage);
  }

  @BeforeEach
  void setup() {
    report = Mockito.mock(Report.class);
    line1 = Mockito.mock(ReportLine.class);
    line2 = Mockito.mock(ReportLine.class);
    lines = List.of(line1, line2);
    Mockito.when(report.getLines()).thenReturn(lines);
  }

  @Test
  void loadCoverage_returns_coverage_for_instruction() {
    Mockito.when(line1.getInstructionMissed()).thenReturn(100L);
    Mockito.when(line1.getInstructionCovered()).thenReturn(50L);

    Mockito.when(line2.getInstructionMissed()).thenReturn(30L);
    Mockito.when(line2.getInstructionCovered()).thenReturn(50L);

    Coverage actual = new Coverage(Coverage.CoverageCategory.INSTRUCTION, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.INSTRUCTION);
    actual.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_method() {
    Mockito.when(line1.getMethodMissed()).thenReturn(100L);
    Mockito.when(line1.getMethodCovered()).thenReturn(50L);

    Mockito.when(line2.getMethodMissed()).thenReturn(30L);
    Mockito.when(line2.getMethodCovered()).thenReturn(50L);

    Coverage actual = new Coverage(Coverage.CoverageCategory.METHOD, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.METHOD);
    actual.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_line() {
    Mockito.when(line1.getLineMissed()).thenReturn(100L);
    Mockito.when(line1.getLineCovered()).thenReturn(50L);

    Mockito.when(line2.getLineMissed()).thenReturn(30L);
    Mockito.when(line2.getLineCovered()).thenReturn(50L);

    Coverage actual = new Coverage(Coverage.CoverageCategory.LINE, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.LINE);
    actual.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_branch() {
    Mockito.when(line1.getBranchMissed()).thenReturn(100L);
    Mockito.when(line1.getBranchCovered()).thenReturn(50L);

    Mockito.when(line2.getBranchMissed()).thenReturn(30L);
    Mockito.when(line2.getBranchCovered()).thenReturn(50L);

    Coverage actual = new Coverage(Coverage.CoverageCategory.BRANCH, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.BRANCH);
    actual.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_complexity() {
    Mockito.when(line1.getComplexityMissed()).thenReturn(100L);
    Mockito.when(line1.getComplexityCovered()).thenReturn(50L);

    Mockito.when(line2.getComplexityMissed()).thenReturn(30L);
    Mockito.when(line2.getComplexityCovered()).thenReturn(50L);

    Coverage actual = new Coverage(Coverage.CoverageCategory.COMPLEXITY, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.COMPLEXITY);
    actual.loadCoverage();
    assertEquals(expected, actual);
  }

}
