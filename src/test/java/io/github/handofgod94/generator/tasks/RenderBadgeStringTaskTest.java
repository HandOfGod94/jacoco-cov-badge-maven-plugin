package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.generator.BadgeProcessState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RenderBadgeStringTaskTest {


  private File jacocoReport;
  private BadgeProcessState state;
  private File mockOutputFile;

  @BeforeEach
  void setUp() throws URISyntaxException {
    mockOutputFile = Mockito.mock(File.class);
    jacocoReport = new File(getClass().getClassLoader().getResource("jacoco.csv").toURI());
    state = new BadgeProcessState(jacocoReport, mockOutputFile, Badge.CoverageCategory.INSTRUCTION, "foo");
  }

  @Test
  void perform_generates_rendered_template_string_with_badge_values() {
    Badge badge = new Badge("foo", 10);

    state.setBadge(badge);
    Task task1 = new LoadConfigurationTask();
    Task task2 = new RenderBadgeStringTask();
    task1.perform(state);
    task2.perform(state);
    String svgString = state.getRenderedString();

    assertTrue(svgString.contains("foo"));
    assertTrue(svgString.contains("10%"));
  }
}
