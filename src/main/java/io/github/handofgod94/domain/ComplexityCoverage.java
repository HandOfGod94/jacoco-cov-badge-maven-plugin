package io.github.handofgod94.domain;

public class ComplexityCoverage extends Coverage implements CoverageInterface {

  ComplexityCoverage(CoverageCategory category, Report report) {
    super(category, report);
  }

  @Override
  public long calculateMissed() {
    return report.getLines().map(ReportLine::getComplexityMissed).sum().longValue();
  }

  @Override
  public long calculateCovered() {
    return report.getLines().map(ReportLine::getComplexityCovered).sum().longValue();
  }
}
