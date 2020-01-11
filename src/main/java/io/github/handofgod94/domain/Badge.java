package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Range;
import io.github.handofgod94.domain.coverage.Coverage;
import io.vavr.collection.HashMap;
import io.vavr.control.Try;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDMMType1Font;

import java.util.Map;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

/**
 * Badge class to hold configuration obtained from Maven config.
 * This will be shared across all the classes, who needs badge related information.
 */
@AutoValue
public abstract class Badge {

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

  protected float calculateWidth(String str) {
    PDFont font = PDMMType1Font.HELVETICA_BOLD;
    int fontSize = 12;
    Try<Float> stringWidth = Try.of(() -> font.getStringWidth(str));

    float width = Match(stringWidth).of(
        Case($Success($()), val -> (val / 1000 * fontSize + 10.0f)),
        Case($Failure($()), () -> Coverage.INVALID_COVERAGE_PERCENTAGE)
    );

    return width;
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
