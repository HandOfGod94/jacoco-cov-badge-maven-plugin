package io.github.handofgod94.generator.helper;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoverageHelperTest {

  private Report report;
  private ReportLine line1;
  private ReportLine line2;
  private List<ReportLine> lines;

  @BeforeEach
  void setup() {
    report = Mockito.mock(Report.class);
    line1 = Mockito.mock(ReportLine.class);
    line2 = Mockito.mock(ReportLine.class);
    lines = Arrays.asList(line1, line2);
    Mockito.when(report.getLines()).thenReturn(lines);
  }

  @Test
  void loadCoverage_returns_coverage_for_instruction() {
    Mockito.when(line1.getjInstructionMissed()).thenReturn(100L);
    Mockito.when(line1.getjInstructionCovered()).thenReturn(50L);

    Mockito.when(line2.getjInstructionMissed()).thenReturn(30L);
    Mockito.when(line2.getjInstructionCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.INSTRUCTION, report);
    Coverage expected = new Coverage(100L, 130L, Badge.CoverageCategory.INSTRUCTION);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_method() {
    Mockito.when(line1.getjMethodMissed()).thenReturn(100L);
    Mockito.when(line1.getjMethodCovered()).thenReturn(50L);

    Mockito.when(line2.getjMethodMissed()).thenReturn(30L);
    Mockito.when(line2.getjMethodCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.METHOD, report);
    Coverage expected = new Coverage(100L, 130L, Badge.CoverageCategory.METHOD);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_line() {
    Mockito.when(line1.getjLineMissed()).thenReturn(100L);
    Mockito.when(line1.getjLineCovered()).thenReturn(50L);

    Mockito.when(line2.getjLineMissed()).thenReturn(30L);
    Mockito.when(line2.getjLineCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.LINE, report);
    Coverage expected = new Coverage(100L, 130L, Badge.CoverageCategory.LINE);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_branch() {
    Mockito.when(line1.getjBranchMissed()).thenReturn(100L);
    Mockito.when(line1.getjBranchCovered()).thenReturn(50L);

    Mockito.when(line2.getjBranchMissed()).thenReturn(30L);
    Mockito.when(line2.getjBranchCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.BRANCH, report);
    Coverage expected = new Coverage(100L, 130L, Badge.CoverageCategory.BRANCH);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_complexity() {
    Mockito.when(line1.getjComplexityMissed()).thenReturn(100L);
    Mockito.when(line1.getjComplexityCovered()).thenReturn(50L);

    Mockito.when(line2.getjComplexityMissed()).thenReturn(30L);
    Mockito.when(line2.getjComplexityCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.COMPLEXITY, report);
    Coverage expected = new Coverage(100L, 130L, Badge.CoverageCategory.COMPLEXITY);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }
}
