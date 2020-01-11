package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Range;
import io.github.handofgod94.domain.coverage.Coverage;
import io.vavr.collection.HashMap;
import io.vavr.control.Try;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDMMType1Font;

import java.util.Map;

/**
 * Badge class to hold configuration obtained from Maven config.
 * This will be shared across all the classes, who needs badge related information.
 */
@AutoValue
public abstract class Badge {

  private static final PDFont FONT_FACE = PDMMType1Font.HELVETICA_BOLD;
  private static final int FONT_SIZE = 12;

  public static Badge create(String badgeLabel, int badgeValue) {
    return new AutoValue_Badge(badgeLabel, badgeValue);
  }

  /**
   * Calculates the color code for the badge.
   * This is useful for template to render the background color of the badge
   * @return String representing color in hex code format.
   */
  public String resultColorCode() {
    int badgeValue = getBadgeValue();
    if (Range.closedOpen(0, 40).contains(badgeValue)) {
      return BadgeColors.RED.getColorCode();
    } else if (Range.closedOpen(40, 50).contains(badgeValue)) {
      return BadgeColors.ORANGE.getColorCode();
    } else if (Range.closedOpen(40, 60).contains(badgeValue)) {
      return BadgeColors.YELLOW.getColorCode();
    } else if (Range.closedOpen(60, 70).contains(badgeValue)) {
      return BadgeColors.YELLOWGREEN.getColorCode();
    } else if (Range.closedOpen(70, 80).contains(badgeValue)) {
      return BadgeColors.GREEN.getColorCode();
    } else {
      return BadgeColors.BRIGHTGREEN.getColorCode();
    }
  }

  public float labelWidth() {
    return calculateWidth(getBadgeLabel());
  }

  public float valueWidth() {
    return calculateWidth(getBadgeValue() + "%");
  }

  private float calculateWidth(String str) {
    return renderingWidth(str) / 1000 * FONT_SIZE + 10.0f;
  }

  private float renderingWidth(String string) {
    return
      Try
        .of(() -> FONT_FACE.getStringWidth(string))
        .getOrElse(Coverage.INVALID_COVERAGE_PERCENTAGE);
  }

  public Map<String, Badge> templateData() {
    return HashMap.of("badge", this).toJavaMap();
  }

  // Getters

  /**
   * Badge label. Default is "coverage"
   * @return the badgeLabel
   */
  public abstract String getBadgeLabel();

  /**
   * Badge Value i.e. %age
   * @return the badgeValue
   */
  public abstract int getBadgeValue();

}
