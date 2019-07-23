package io.github.handofgod94.service.helper;

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
        return Coverage.create(covered, missed, category);
      case LINE:
        covered = totalCoveredLine();
        missed = totalMissedLine();
        return Coverage.create(covered, missed, category);
      case BRANCH:
        covered = totalCoveredBranch();
        missed = totalMissedBranch();
        return Coverage.create(covered, missed, category);
      case METHOD:
        covered = totalCoveredMethod();
        missed = totalMissedMethod();
        return Coverage.create(covered, missed, category);
      case COMPLEXITY:
        covered = totalCoveredComplexity();
        missed = totalMissedComplexity();
        return Coverage.create(covered, missed, category);
      default:
        throw new IllegalArgumentException("Invalid Coverage Category provided");
    }
  }


  // missed instructions
  private final long totalMissedInstruction() {
    return report.getLines().map(ReportLine::getInstructionMissed).sum().longValue();
  }

  private final long totalMissedLine() {
    return report.getLines().map(ReportLine::getLineMissed).sum().longValue();
  }

  private final long totalMissedBranch() {
    return report.getLines().map(ReportLine::getBranchMissed).sum().longValue();
  }

  private final long totalMissedMethod() {
    return report.getLines().map(ReportLine::getMethodMissed).sum().longValue();
  }

  private final long totalMissedComplexity() {
    return report.getLines().map(ReportLine::getComplexityMissed).sum().longValue();
  }


  // covered instructions
  private final long totalCoveredInstruction() {
    return report.getLines().map(ReportLine::getInstructionCovered).sum().longValue();
  }

  private final long totalCoveredLine() {
    return report.getLines().map(ReportLine::getLineCovered).sum().longValue();
  }

  private final long totalCoveredBranch() {
    return report.getLines().map(ReportLine::getBranchCovered).sum().longValue();
  }

  private final long totalCoveredMethod() {
    return report.getLines().map(ReportLine::getMethodCovered).sum().longValue();
  }

  private final long totalCoveredComplexity() {
    return report.getLines().map(ReportLine::getComplexityCovered).sum().longValue();
  }
}
