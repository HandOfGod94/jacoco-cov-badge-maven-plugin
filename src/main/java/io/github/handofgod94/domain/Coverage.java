package io.github.handofgod94.domain;

public class Coverage {
  private final int covered;
  private final int missed;

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  Coverage(int covered, int missed) {
    this.covered = covered;
    this.missed = missed;
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
}
