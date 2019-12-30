package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.Report;
import io.vavr.Lazy;

import java.util.Objects;

public abstract class Coverage implements CoverageCalculator {

  /**
   * Enum constants for coverage category.
   * Jacoco CSV Report generates results of various categories
   * such as branch missed/covered, instructions missed/covered.
   * This enum maps each of those categories.
   *
   * <p>It includes:
   * <code>INSTRUCTION, BRANCH, LINE, COMPLEXITY, METHOD</code>
   */
  public enum CoverageCategory {
    INSTRUCTION,
    BRANCH,
    LINE,
    COMPLEXITY,
    METHOD
  }

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  public long covered;
  public long missed;
  public CoverageCategory category;
  public Report report;
  protected Lazy<CoverageCalculator> calculator = Lazy.of(() -> this);

  Coverage(CoverageCategory category, Report report) {
    this.category = category;
    this.report = report;
  }

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
   * @return float value if coverage calculation is success,
   *     INVALID_COVERAGE_PERCENTAGE = 0f otherwise
   */
  public float getCoveragePercentage() {
    covered = calculator.get().calculateCovered();
    missed = calculator.get().calculateMissed();

    if (covered < 0
        || missed < 0) {
      return INVALID_COVERAGE_PERCENTAGE;
    }

    float totalInstructions = covered + missed;
    float result = (covered / totalInstructions) * 100.0f;
    return result;
  }

  public long getMissed() {
    return missed;
  }

  public long getCovered() {
    return covered;
  }

  @Override
  public String toString() {
    return "Coverage{" +
      "covered=" + covered +
      ", missed=" + missed +
      ", category=" + category +
      ", report=" + report +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coverage coverage = (Coverage) o;
    return covered == coverage.covered &&
      missed == coverage.missed &&
      category == coverage.category &&
      report.equals(coverage.report);
  }

  @Override
  public int hashCode() {
    return Objects.hash(covered, missed, category, report);
  }
}
