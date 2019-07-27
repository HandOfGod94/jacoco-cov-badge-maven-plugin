package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import io.vavr.control.Try;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDMMType1Font;

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
