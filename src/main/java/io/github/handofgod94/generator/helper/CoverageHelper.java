package io.github.handofgod94.generator.helper;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;

public class CoverageHelper {

  private final Badge.CoverageCategory category;
  private final Report report;

  public CoverageHelper(Badge.CoverageCategory category, Report report) {
    this.category = category;
    this.report = report;
  }

  public Coverage loadCoverage() {
    long missed = 0;
    long covered = 0;
    switch (category) {
      case INSTRUCTION:
        covered = totalCoveredInstruction();
        missed = totalMissedInstruction();
        return new Coverage(covered, missed, category);
      case LINE:
        covered = totalCoveredLine();
        missed = totalMissedLine();
        return new Coverage(covered, missed, category);
      case BRANCH:
        covered = totalCoveredBranch();
        missed = totalMissedBranch();
        return new Coverage(covered, missed, category);
      case METHOD:
        covered = totalCoveredMethod();
        missed = totalMissedMethod();
        return new Coverage(covered, missed, category);
      case COMPLEXITY:
        covered = totalCoveredComplexity();
        missed = totalMissedComplexity();
        return new Coverage(covered, missed, category);
      default:
        throw new IllegalArgumentException("Invalid Coverage Category provided");
    }
  }


  // missed instructions
  private final long totalMissedInstruction() {
    return report.getLines().stream().map(ReportLine::getjInstructionMissed).reduce(0L, Long::sum);
  }

  private final long totalMissedLine() {
    return report.getLines().stream().map(ReportLine::getjLineMissed).reduce(0L, Long::sum);
  }

  private final long totalMissedBranch() {
    return report.getLines().stream().map(ReportLine::getjBranchMissed).reduce(0L, Long::sum);
  }

  private final long totalMissedMethod() {
    return report.getLines().stream().map(ReportLine::getjMethodMissed).reduce(0L, Long::sum);
  }

  private final long totalMissedComplexity() {
    return report.getLines().stream().map(ReportLine::getjComplexityMissed).reduce(0L, Long::sum);
  }


  // covered instructions
  private final long totalCoveredInstruction() {
    return report.getLines().stream().map(ReportLine::getjInstructionCovered).reduce(0L, Long::sum);
  }

  private final long totalCoveredLine() {
    return report.getLines().stream().map(ReportLine::getjLineCovered).reduce(0L, Long::sum);
  }

  private final long totalCoveredBranch() {
    return report.getLines().stream().map(ReportLine::getjBranchCovered).reduce(0L, Long::sum);
  }

  private final long totalCoveredMethod() {
    return report.getLines().stream().map(ReportLine::getjMethodCovered).reduce(0L, Long::sum);
  }

  private final long totalCoveredComplexity() {
    return report.getLines().stream().map(ReportLine::getjComplexityCovered).reduce(0L, Long::sum);
  }
}
