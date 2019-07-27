package io.github.handofgod94.service.helper;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    lines = List.of(line1, line2);
    Mockito.when(report.getLines()).thenReturn(lines);
  }

  @Test
  void loadCoverage_returns_coverage_for_instruction() {
    Mockito.when(line1.getInstructionMissed()).thenReturn(100L);
    Mockito.when(line1.getInstructionCovered()).thenReturn(50L);

    Mockito.when(line2.getInstructionMissed()).thenReturn(30L);
    Mockito.when(line2.getInstructionCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.INSTRUCTION, report);
    Coverage expected = Coverage.create(100L, 130L, Badge.CoverageCategory.INSTRUCTION);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_method() {
    Mockito.when(line1.getMethodMissed()).thenReturn(100L);
    Mockito.when(line1.getMethodCovered()).thenReturn(50L);

    Mockito.when(line2.getMethodMissed()).thenReturn(30L);
    Mockito.when(line2.getMethodCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.METHOD, report);
    Coverage expected = Coverage.create(100L, 130L, Badge.CoverageCategory.METHOD);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_line() {
    Mockito.when(line1.getLineMissed()).thenReturn(100L);
    Mockito.when(line1.getLineCovered()).thenReturn(50L);

    Mockito.when(line2.getLineMissed()).thenReturn(30L);
    Mockito.when(line2.getLineCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.LINE, report);
    Coverage expected = Coverage.create(100L, 130L, Badge.CoverageCategory.LINE);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_branch() {
    Mockito.when(line1.getBranchMissed()).thenReturn(100L);
    Mockito.when(line1.getBranchCovered()).thenReturn(50L);

    Mockito.when(line2.getBranchMissed()).thenReturn(30L);
    Mockito.when(line2.getBranchCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.BRANCH, report);
    Coverage expected = Coverage.create(100L, 130L, Badge.CoverageCategory.BRANCH);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }

  @Test
  void loadCoverage_returns_coverage_for_complexity() {
    Mockito.when(line1.getComplexityMissed()).thenReturn(100L);
    Mockito.when(line1.getComplexityCovered()).thenReturn(50L);

    Mockito.when(line2.getComplexityMissed()).thenReturn(30L);
    Mockito.when(line2.getComplexityCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Badge.CoverageCategory.COMPLEXITY, report);
    Coverage expected = Coverage.create(100L, 130L, Badge.CoverageCategory.COMPLEXITY);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }
}
