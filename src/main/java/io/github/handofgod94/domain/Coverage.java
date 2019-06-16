package io.github.handofgod94.domain;

public class Coverage {
  private final int instructionCovered;
  private final int instructionMissed;

  public static final float INVALID_COVERAGE_PERCENTAGE = 0f;

  Coverage(int instructionCovered, int instructionMissed) {
    this.instructionCovered = instructionCovered;
    this.instructionMissed = instructionMissed;
  }

  public float getCoveragePercentage() {
    if (this.instructionCovered < 0
        || this.instructionMissed < 0) {
          return 0f;
        }

    float totalInstructions = this.instructionCovered + this.instructionMissed;
    float result = (this.instructionCovered / totalInstructions) * 100.0f;
    return result;
  }
}
