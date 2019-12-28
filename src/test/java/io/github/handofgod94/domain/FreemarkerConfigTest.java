package io.github.handofgod94.domain;

import freemarker.template.Configuration;
import io.github.handofgod94.MyMojo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class FreemarkerConfigTest {

  @Test
  void getConfiguration_loads_freemarker_config() {
    // TODO: see if we can test other configs as well

    Configuration actual = new FreemarkerConfig().getConfiguration();
    assertAll("it loadsd all the freemarker config",
      () -> assertEquals("UTF-8", actual.getDefaultEncoding()),
      () -> assertEquals(Locale.US, actual.getLocale())
    );
  }

}
