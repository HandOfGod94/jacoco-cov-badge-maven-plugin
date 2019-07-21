package io.github.handofgod94.domain;

import java.util.Objects;

public class Coverage {
  private final long covered;
  private final long missed;
  private final Badge.CoverageCategory category;

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  public Coverage(long covered, long missed, Badge.CoverageCategory category) {
    this.covered = covered;
    this.missed = missed;
    this.category = category;
  }

  public float getCoveragePercentage() {
    if (this.covered < 0
        || this.missed < 0) {
      return 0f;
    }

    float totalInstructions = this.covered + this.missed;
    float result = (this.covered / totalInstructions) * 100.0f;
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coverage coverage = (Coverage) o;
    return covered == coverage.covered
        && missed == coverage.missed
        && category == coverage.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(covered, missed, category);
  }

  @Override
  public String toString() {
    return "Coverage{"
      + "covered=" + covered
      + ", missed=" + missed
      + ", category=" + category
      + '}';
  }
}
