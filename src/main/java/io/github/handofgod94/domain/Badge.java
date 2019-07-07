package io.github.handofgod94.domain;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDMMType1Font;

import java.io.IOException;
import java.util.Objects;

/**
 * Badge class to hold configuration obtained from Maven config.
 * This will be shared across all the classes, who needs badge related information.
 */
public class Badge {

  private final String badgeLabel;
  private final int badgeValue;

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

  public Badge(String badgeLabel, int badgeValue) {
    this.badgeLabel = badgeLabel;
    this.badgeValue = badgeValue;
  }

  public String resultColorCode() {
    if (badgeValue >= 0 && badgeValue < 40) {
      return BadgeColors.RED.getColorCode();
    } else if (badgeValue >= 40 && badgeValue < 50) {
      return BadgeColors.ORANGE.getColorCode();
    } else if (badgeValue >= 50 && badgeValue < 60) {
      return BadgeColors.YELLOW.getColorCode();
    } else if (badgeValue >= 60 && badgeValue < 70) {
      return BadgeColors.YELLOWGREEN.getColorCode();
    } else if (badgeValue >= 70 && badgeValue < 80) {
      return BadgeColors.GREEN.getColorCode();
    } else {
      return BadgeColors.BRIGHTGREEN.getColorCode();
    }
  }

  public float labelWidth() throws IOException {
    return calculateWidth(badgeLabel);
  }

  public float valueWidth() throws IOException {
    return calculateWidth(String.valueOf(badgeValue));
  }

  private float calculateWidth(String str) throws IOException {
    PDFont font = PDMMType1Font.HELVETICA_BOLD;
    int fontSize = 12;
    float width = ((font.getStringWidth(str) / 1000) * fontSize) + 10.0f;
    return width;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Badge badge = (Badge) o;
    return badgeValue == badge.badgeValue &&
      badgeLabel.equals(badge.badgeLabel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(badgeLabel, badgeValue);
  }

  // Getters

  /**
   * Badge label. Default is "coverage"
   * @return the badgeLabel
   */
  public String getBadgeLabel() {
    return badgeLabel;
  }

  /**
   * Badge Value i.e. %age
   * @return the badgeValue
   */
  public int getBadgeValue() {
    return badgeValue;
  }

  @Override
  public String toString() {
    return "Badge{" +
      "badgeLabel='" + badgeLabel + '\'' +
      ", badgeValue=" + badgeValue +
      '}';
  }
}
