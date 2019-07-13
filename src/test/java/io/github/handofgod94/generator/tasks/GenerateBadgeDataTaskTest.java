package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.generator.BadgeProcessState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateBadgeDataTaskTest {

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
  void perform_generates_badge_data() {
    Coverage mockCoverage = Mockito.mock(Coverage.class);
    Mockito.when(mockCoverage.getCoveragePercentage()).thenReturn(30.0f);
    state.setCoverage(mockCoverage);

    Task task = new GenerateBadgeDataTask();
    task.perform(state);

    Badge expected = new Badge("foo", 30);
    Badge actual = state.getBadge();

    assertEquals(expected, actual);

  }
}
