package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.CoverageCategory;
import io.github.handofgod94.domain.Report;
import io.vavr.Lazy;

import java.util.Objects;

public abstract class Coverage implements CoverageCalculator {

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  public CoverageCategory category;
  public Report report;
  protected Lazy<CoverageCalculator> calculator = Lazy.of(() -> this);
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
   * INVALID_COVERAGE_PERCENTAGE = 0f otherwise
   */
  public float getCoveragePercentage() {
    long covered = this.covered.get();
    long missed = this.missed.get();

    if (covered < 0
      || missed < 0) {
      return INVALID_COVERAGE_PERCENTAGE;
    }

    float totalInstructions = covered + missed;
    float result = (covered / totalInstructions) * 100.0f;
    return result;
  }

  @Override
  public String toString() {
    return "Coverage{"
      + "covered=" + covered
      + ", missed=" + missed
      + ", category=" + category
      + ", report=" + report
      + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coverage coverage = (Coverage) o;
    return covered == coverage.covered
      && missed == coverage.missed
      && category == coverage.category
      && report.equals(coverage.report);
  }

  @Override
  public int hashCode() {
    return Objects.hash(covered, missed, category, report);
  }
}
