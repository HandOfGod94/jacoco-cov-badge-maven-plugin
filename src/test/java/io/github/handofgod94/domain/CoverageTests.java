package io.github.handofgod94.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoverageTests {

  @Test
  void testGetCoverageForValidInput() {
    Coverage coverage = new Coverage(10, 10);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(Float.valueOf(50.0f), actualPercentage);
  }

  @Test
  void testGetCoverageForInvalidInput() {
    Coverage coverage = new Coverage(-10, 10);
    float actualPercentage = coverage.getCoveragePercentage();
    assertEquals(0f, actualPercentage);
  }

}
