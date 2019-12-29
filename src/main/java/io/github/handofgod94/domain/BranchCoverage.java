package io.github.handofgod94.domain;

public class BranchCoverage extends Coverage implements CoverageInterface {

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
