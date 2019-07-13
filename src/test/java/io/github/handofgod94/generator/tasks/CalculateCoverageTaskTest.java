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

class CalculateCoverageTaskTest {

  private File jacocoReport;
  private File mockOutputFile;
  private BadgeProcessState state;

  @BeforeEach
  void setup() throws URISyntaxException {
    jacocoReport = new File(getClass().getClassLoader().getResource("jacoco.csv").toURI());
    mockOutputFile = Mockito.mock(File.class);
    state = new BadgeProcessState(jacocoReport, mockOutputFile, Badge.CoverageCategory.INSTRUCTION, "foo");
  }

  @Test
  void perform_calculates_coverage_based_on_file() {
    Coverage expected = new Coverage( 528L,  171L, Badge.CoverageCategory.INSTRUCTION);
    Task task1 = new LoadConfigurationTask();
    Task task2 = new CalculateCoverageTask();
    task1.perform(state);
    task2.perform(state);

    Coverage actual = state.getCoverage();
    assertEquals(expected, actual);
  }
}
