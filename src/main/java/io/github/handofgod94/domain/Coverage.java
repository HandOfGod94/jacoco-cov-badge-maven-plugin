package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Coverage {

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  public abstract long getCovered();
  public abstract long getMissed();

  public abstract Badge.CoverageCategory getCategory();

  public static Coverage create(long covered, long missed, Badge.CoverageCategory category) {
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
