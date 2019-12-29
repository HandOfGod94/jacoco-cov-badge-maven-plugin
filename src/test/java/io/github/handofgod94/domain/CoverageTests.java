package io.github.handofgod94.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoverageTests {

  @Test
  void getCoveragePercentage_returns_coverage_for_valid_input() {
    Coverage coverage = Coverage.create(10, 10, Coverage.CoverageCategory.INSTRUCTION);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(Float.valueOf(50.0f), actualPercentage);
  }

  @Test
  void getCoveragePercentage_returns_zero_for_invalid_input() {
    Coverage coverage = Coverage.create(-10, 10, Coverage.CoverageCategory.INSTRUCTION);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(0f, actualPercentage);
  }

}
