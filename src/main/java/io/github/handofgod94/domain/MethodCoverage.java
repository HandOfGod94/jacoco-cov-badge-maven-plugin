package io.github.handofgod94.domain;

public class MethodCoverage extends Coverage implements CoverageInterface {

  MethodCoverage(CoverageCategory category, Report report) {
    super(category, report);
  }

  @Override
  public long calculateMissed() {
    return report.getLines().map(ReportLine::getMethodMissed).sum().longValue();
  }

  @Override
  public long calculateCovered() {
    return report.getLines().map(ReportLine::getMethodCovered).sum().longValue();
  }
}
