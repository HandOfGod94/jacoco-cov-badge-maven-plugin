package org.gahan;

import java.io.IOException;

/**
 * BadgeConfig obtained from xml
 */
public class Badge {

  private String badgeLabel = "coverage";
  private int badgeValue;
  private String resultColor;
  private float badgeLabelWidth = 0.0f;
  private float badgeValueWidth = 0.0f;

  public Badge(int badgeValue, String resultColor) throws IOException {
    this.badgeValue = badgeValue;
    this.resultColor = resultColor;
    this.badgeLabelWidth = BadgeUtility.calculateWidth(this.badgeLabel);
    this.badgeValueWidth = BadgeUtility.calculateWidth(this.badgeValue + "%");
  }

  public Badge(String badgeLabel, String resultColor, int badgeValue) throws IOException {
    this.badgeLabel = badgeLabel;
    this.resultColor = resultColor;
    this.badgeValue = badgeValue;
    this.badgeLabelWidth = BadgeUtility.calculateWidth(this.badgeLabel);
    this.badgeValueWidth = BadgeUtility.calculateWidth(this.badgeValue + "%");
  }

  // Getters

  /**
   * @return the badgeLabel
   */
  public String getBadgeLabel() {
    return badgeLabel;
  }

  /**
   * @return the badgeValue
   */
  public int getBadgeValue() {
    return badgeValue;
  }

  /**
   * @return the resultColor
   */
  public String getResultColor() {
    return resultColor;
  }

  /**
   * @return the badgeLabelWidth
   */
  public float getBadgeLabelWidth() {
    return badgeLabelWidth;
  }

  /**
   * @return the badgeValueWidth
   */
  public float getBadgeValueWidth() {
    return badgeValueWidth;
  }
}
