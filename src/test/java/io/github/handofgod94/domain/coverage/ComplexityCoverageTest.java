package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComplexityCoverageTest {

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

    when(line1.getComplexityMissed()).thenReturn(100L);
    when(line1.getComplexityCovered()).thenReturn(50L);

    when(line2.getComplexityMissed()).thenReturn(30L);
    when(line2.getComplexityCovered()).thenReturn(50L);
  }


  @Test
  void calculateMissed_calculatesComplexityMissed() {
    Coverage coverage = Coverage.create(CoverageCategory.COMPLEXITY, report);

    long actual = coverage.calculateMissed();

    assertEquals(130L, actual);
  }

  @Test
  void calculateCovered_calculatesComplexityCovered() {
    Coverage coverage = Coverage.create(CoverageCategory.COMPLEXITY, report);

    long actual = coverage.calculateCovered();

    assertEquals(100L, actual);
  }
}
