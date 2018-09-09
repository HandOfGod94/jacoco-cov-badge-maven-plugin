package org.gahan;

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
   * Badge configuration holder.
   * @param badgeValue Value to be displayed in the badge i.e. coverage %age
   * @param resultColor color of the value in badge
   * @throws IOException Exception when trying to calculate width and height of badge
   */
  public Badge(int badgeValue, String resultColor) throws IOException {
    this.badgeValue = badgeValue;
    this.resultColor = resultColor;
    this.badgeLabelWidth = BadgeUtility.calculateWidth(this.badgeLabel);
    this.badgeValueWidth = BadgeUtility.calculateWidth(this.badgeValue + "%");
  }

  /**
   * Badge configuration holder.
   * @param badgeLabel Text label for the badge
   * @param resultColor color of the value in badge
   * @param badgeValue Text value to be displayed in badge i.e. coverage %age
   * @throws IOException Exception while trying to calculate width and height of badge.
   */
  public Badge(String badgeLabel, String resultColor, int badgeValue) throws IOException {
    this.badgeLabel = badgeLabel;
    this.resultColor = resultColor;
    this.badgeValue = badgeValue;
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
