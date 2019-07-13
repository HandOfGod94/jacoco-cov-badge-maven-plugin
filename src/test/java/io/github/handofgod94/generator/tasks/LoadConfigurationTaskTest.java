package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.generator.BadgeProcessState;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadConfigurationTaskTest {

  @Test
  void perform_loads_freemarker_configuration() {
    File mockReport = Mockito.mock(File.class);
    File mockOutputFile = Mockito.mock(File.class);
    Badge.CoverageCategory mockCategory = Badge.CoverageCategory.INSTRUCTION;
    BadgeProcessState state = new BadgeProcessState(mockReport, mockOutputFile, mockCategory, "foo");
    Task loadConfigTask = new LoadConfigurationTask();
    loadConfigTask.perform(state);

    // just sanity to verify config api is wrong.
    assertEquals(state.getConfiguration().getDefaultEncoding(), "UTF-8");
  }
}
