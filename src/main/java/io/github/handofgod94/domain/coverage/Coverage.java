package io.github.handofgod94.domain.coverage;

import io.github.handofgod94.domain.Report;

import java.util.Objects;

public class Coverage {

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

  Coverage(CoverageCategory category, Report report) {
    this.category = category;
    this.report = report;
  }

  public static Coverage create(CoverageCategory category, Report report) {
    return new Coverage(category, report);
  }

  /**
   * Calculates coverage percentage.
   * @return float value if coverage calculation is success,
   *     INVALID_COVERAGE_PERCENTAGE = 0f otherwise
   */
  public float getCoveragePercentage() {
    if (covered < 0
        || missed < 0) {
      return INVALID_COVERAGE_PERCENTAGE;
    }

    float totalInstructions = covered + missed;
    float result = (covered / totalInstructions) * 100.0f;
    return result;
  }

  /**
   * Generates an instance of Coverage by calculating the coverage for given category.
   */
  public void loadCoverage() {
    switch (category) {
      case INSTRUCTION:
        InstructionCoverage coverage = new InstructionCoverage(category, report);
        covered = coverage.calculateCovered();
        missed = coverage.calculateMissed();
        break;
      case LINE:
        LineCoverage coverage1 = new LineCoverage(category, report);
        covered = coverage1.calculateCovered();
        missed = coverage1.calculateMissed();
        break;
      case BRANCH:
        BranchCoverage coverage2 = new BranchCoverage(category, report);
        covered = coverage2.calculateCovered();
        missed = coverage2.calculateMissed();
        break;
      case METHOD:
        MethodCoverage methodCoverage = new MethodCoverage(category, report);
        covered = methodCoverage.calculateCovered();
        missed = methodCoverage.calculateMissed();
        break;
      case COMPLEXITY:
        ComplexityCoverage complexityCoverage = new ComplexityCoverage(category, report);
        covered = complexityCoverage.calculateCovered();
        missed = complexityCoverage.calculateMissed();
        break;
      default:
        throw new IllegalArgumentException("Invalid Coverage Category provided");
    }
  }

  public long getCovered() {
    return covered;
  }

  public long getMissed() {
    return missed;
  }

  // TODO: see if setters can be removed
  public void setCovered(long covered) {
    this.covered = covered;
  }

  public void setMissed(long missed) {
    this.missed = missed;
  }

  @Override
  public String toString() {
    return "Coverage{" +
      "covered=" + covered +
      ", missed=" + missed +
      ", category=" + category +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coverage coverage = (Coverage) o;
    return covered == coverage.covered &&
      missed == coverage.missed &&
      category == coverage.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(covered, missed, category);
  }
}
