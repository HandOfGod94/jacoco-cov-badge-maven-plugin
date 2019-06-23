package io.github.handofgod94.domain;

import java.io.IOException;

/**
 * Badge class to hold configuration obtained from Maven config.
 * This will be shared across all the classes, who needs badge related information.
 */
public class Badge {

  private String badgeLabel = "coverage";
  private int badgeValue;
  private String resultColor;
  private float badgeLabelWidth = 0.0f;
  private float badgeValueWidth = 0.0f;

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

  /**
   * Badge configuration holder.
   * @param badgeValue Value to be displayed in the badge i.e. coverage %age
   * @throws IOException Exception when trying to calculate width and height of badge
   */
  public Badge(int badgeValue) throws IOException {
    this.badgeValue = badgeValue;
    this.resultColor = BadgeUtility.getColorFromRange(badgeValue).getColorCode();
    this.badgeLabelWidth = BadgeUtility.calculateWidth(this.badgeLabel);
    this.badgeValueWidth = BadgeUtility.calculateWidth(this.badgeValue + "%");
  }

  /**
   * Badge configuration holder.
   * @param badgeLabel Text label for the badge
   * @param badgeValue Text value to be displayed in badge i.e. coverage %age
   * @throws IOException Exception while trying to calculate width and height of badge.
   */
  public Badge(String badgeLabel, int badgeValue) throws IOException {
    this.badgeLabel = badgeLabel;
    this.badgeValue = badgeValue;
    this.resultColor = BadgeUtility.getColorFromRange(badgeValue).getColorCode();
    this.badgeLabelWidth = BadgeUtility.calculateWidth(this.badgeLabel);
    this.badgeValueWidth = BadgeUtility.calculateWidth(this.badgeValue + "%");
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

  /**
   * Color of the badge.
   * @return the resultColor
   */
  public String getResultColor() {
    return resultColor;
  }

  /**
   * Width of the label of the badge.
   * @return the badgeLabelWidth
   */
  public float getBadgeLabelWidth() {
    return badgeLabelWidth;
  }

  /**
   * Width of value of the badge.
   * @return the badgeValueWidth
   */
  public float getBadgeValueWidth() {
    return badgeValueWidth;
  }
}
