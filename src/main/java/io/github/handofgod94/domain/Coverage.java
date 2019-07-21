package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Coverage {

  public abstract long getCovered();
  public abstract long getMissed();
  public abstract Badge.CoverageCategory getCategory();

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  public static Coverage create(long covered, long missed, Badge.CoverageCategory category) {
    return new AutoValue_Coverage(covered, missed, category);
  }

  public float getCoveragePercentage() {
    long covered = getCovered();
    long missed = getMissed();
    if (covered < 0
        || missed < 0) {
      return 0f;
    }

    float totalInstructions = covered + missed;
    float result = (covered / totalInstructions) * 100.0f;
    return result;
  }
}
