package io.github.handofgod94.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeTest {

  @ParameterizedTest
  @MethodSource("colorMap")
  void resultColorCode_returns_correct_color_code_for_each_percentage_range(int badgeValue, BadgeColors color) {
    Badge badge = Badge.create("foo", badgeValue);
    String expected = color.getColorCode();
    String actual = badge.resultColorCode();
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> colorMap() {
    return Stream.of(
      Arguments.of(39, BadgeColors.RED),
      Arguments.of(49, BadgeColors.ORANGE),
      Arguments.of(59, BadgeColors.YELLOW),
      Arguments.of(69, BadgeColors.YELLOWGREEN),
      Arguments.of(79, BadgeColors.GREEN),
      Arguments.of(99, BadgeColors.BRIGHTGREEN)
    );
  }

  @Test
  void calculateLabelWidth_returns_width_of_label_to_be_rendered_based_on_string() throws IOException {
    Badge badge = Badge.create("foo", 20);
    float expected = 28.66f;
    float actual = badge.labelWidth();
    assertEquals(expected, actual);
  }

  @Test
  void calculateValueWidth_returns_width_of_value_to_be_rendered_based_on_string() throws IOException {
    Badge badge = Badge.create("foo", 20);
    float expected = 34.012f;
    float actual = badge.valueWidth();
    assertEquals(expected, actual);
  }
}
