package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.CoverageCategory;
import io.github.handofgod94.domain.Report;
import io.vavr.Lazy;

public abstract class Coverage implements CoverageCalculator {

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;
  public static final float INVALID_TOTAL = 1f;

  public CoverageCategory category;
  public Report report;

  protected Lazy<Long> covered = Lazy.of(this::calculateCovered);
  protected Lazy<Long> missed = Lazy.of(this::calculateMissed);

  Coverage(CoverageCategory category, Report report) {
    this.category = category;
    this.report = report;
  }

  /**
   * Creates coverage for a jacoco report.
   *
   * @param category category fro which coverage needs to be calculated.
   * @param report   parsed jacoco report
   * @return Coverage instance based on category.
   */
  public static Coverage create(CoverageCategory category, Report report) {
    switch (category) {
      case INSTRUCTION:
        return new InstructionCoverage(category, report);
      case LINE:
        return new LineCoverage(category, report);
      case BRANCH:
        return new BranchCoverage(category, report);
      case METHOD:
        return new MethodCoverage(category, report);
      case COMPLEXITY:
        return new ComplexityCoverage(category, report);
      default:
        throw new IllegalArgumentException("Invalid Coverage Category provided");
    }
  }

  /**
   * Calculates coverage percentage.
   *
   * @return float value if coverage calculation is success,
   *     INVALID_COVERAGE_PERCENTAGE = 0f otherwise
   */
  public float getCoveragePercentage() {
    if (!isValid()) return INVALID_COVERAGE_PERCENTAGE;
    return covered() / total() * 100.f;
  }

  private float covered() {
    return covered.get();
  }

  private float missed() {
    return missed.get();
  }

  private float total() {
    if (!isTotalGreaterThanZero()) return INVALID_TOTAL;
    return covered() + missed();
  }

  private boolean isTotalGreaterThanZero() {
    return covered() + missed() > 0;
  }

  private boolean isValid() {
    return covered() > 0 && missed() > 0;
  }
}
