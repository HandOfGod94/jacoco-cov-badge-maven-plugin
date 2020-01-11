package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.CoverageCategory;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;

public class BranchCoverage extends Coverage {

  BranchCoverage(CoverageCategory category, Report report) {
    super(category, report);
  }

  @Override
  public long calculateMissed() {
    return report.getLines().map(ReportLine::getBranchMissed).sum().longValue();
  }

  @Override
  public long calculateCovered() {
    return report.getLines().map(ReportLine::getBranchCovered).sum().longValue();
  }
}
