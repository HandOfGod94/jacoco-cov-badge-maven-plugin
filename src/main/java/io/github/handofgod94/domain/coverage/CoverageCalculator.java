package io.github.handofgod94.domain.coverage;

public interface CoverageCalculator {
  long calculateMissed();
  long calculateCovered();
}
