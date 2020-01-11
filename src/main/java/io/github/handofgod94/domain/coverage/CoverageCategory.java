package io.github.handofgod94.domain.coverage;

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
