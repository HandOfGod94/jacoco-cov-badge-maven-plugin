package io.github.handofgod94.generator;

import freemarker.template.TemplateException;
import io.github.handofgod94.domain.Badge;
import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

class BadgeGeneratorTest {

  BadgeGenerator badgeGenerator;

  @BeforeEach
  void setup() throws IOException, TemplateException {
    badgeGenerator = mock(BadgeGenerator.class);
  }

  @Test
  void execute_calls_the_steps_in_proper_sequence() throws IOException, TemplateException, TranscoderException {
    badgeGenerator.execute();
    verify(badgeGenerator, times(1)).loadFreemarkerConfig();

  }
}
