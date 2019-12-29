package io.github.handofgod94.service.helper;

import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void loadCoverage_returns_coverage() {
    Mockito.when(line1.getInstructionMissed()).thenReturn(100L);
    Mockito.when(line1.getInstructionCovered()).thenReturn(50L);

    Mockito.when(line2.getInstructionMissed()).thenReturn(30L);
    Mockito.when(line2.getInstructionCovered()).thenReturn(50L);

    CoverageHelper coverageHelper = new CoverageHelper(Coverage.CoverageCategory.INSTRUCTION, report);
    Coverage expected = Coverage.create(100L, 130L, Coverage.CoverageCategory.INSTRUCTION);
    Coverage actual = coverageHelper.loadCoverage();
    assertEquals(expected, actual);
  }
}
