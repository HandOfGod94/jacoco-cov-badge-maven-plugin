package io.github.handofgod94.service.helper;

import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.domain.Report;

public class CoverageHelper {

  private final Coverage.CoverageCategory category;
  private final Report report;

  public CoverageHelper(Coverage.CoverageCategory category, Report report) {
    this.category = category;
    this.report = report;
  }

  public Coverage loadCoverage() {
    long missed = 0;
    long covered = 0;
    Coverage coverage = Coverage.create(category, report);
    return coverage;
  }
}
