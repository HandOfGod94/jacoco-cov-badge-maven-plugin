package io.github.handofgod94.domain;

public class InstructionCoverage extends Coverage implements CoverageInterface {

  InstructionCoverage(CoverageCategory category, Report report) {
    super(category, report);
  }

  @Override
  public long calculateMissed() {
    return report.getLines().map(ReportLine::getInstructionMissed).sum().longValue();
  }

  @Override
  public long calculateCovered() {
    return report.getLines().map(ReportLine::getInstructionCovered).sum().longValue();
  }
}
