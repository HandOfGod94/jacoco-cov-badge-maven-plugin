package org.gahan;

/**
 * Enum for different colors a badge can have based on report.
 */
public enum BadgeColors {

  RED("#e05d44"),
  ORANGE("#fe7d37"),
  YELLOW("#dfb317"),
  YELLOWGREEN("#a4a61d"),
  GREEN("#97ca00"),
  BRIGHTGREEN("#44cc11");

  // color codes for enums
  private String colorCode;

  BadgeColors(String colorCode) {
    this.colorCode = colorCode;
  }

  /**
   * Color code for the given enum.
   * @return String having hex representation of color code
   */
  public String getColorCode() {
    return this.colorCode;
  }
}
