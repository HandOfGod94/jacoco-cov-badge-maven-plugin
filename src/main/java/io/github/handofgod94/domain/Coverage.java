package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Coverage {

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

  public abstract long getCovered();
  public abstract long getMissed();

  public abstract CoverageCategory getCategory();

  public static Coverage create(long covered, long missed, CoverageCategory category) {
    return new AutoValue_Coverage(covered, missed, category);
  }

  /**
   * Calculates coverage percentage.
   * @return float value if coverage calculation is success,
   *     INVALID_COVERAGE_PERCENTAGE = 0f otherwise
   */
  public float getCoveragePercentage() {
    long covered = getCovered();
    long missed = getMissed();
    if (covered < 0
        || missed < 0) {
      return INVALID_COVERAGE_PERCENTAGE;
    }

    float totalInstructions = covered + missed;
    float result = (covered / totalInstructions) * 100.0f;
    return result;
  }
}
