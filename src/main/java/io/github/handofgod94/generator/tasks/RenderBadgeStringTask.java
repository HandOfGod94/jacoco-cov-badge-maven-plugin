package io.github.handofgod94.generator.tasks;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.generator.BadgeProcessState;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class RenderBadgeStringTask implements Task{

  @Override
  public void perform(BadgeProcessState state) {

    if (state.getBadge() == null) throw new IllegalStateException("Invalid badge provided");
    if (state.getConfiguration() == null) throw new IllegalStateException("Invalid configuration provided");

    Badge badge = state.getBadge();
    Configuration configuration = state.getConfiguration();

    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    Template template = null;
    Writer writer = new StringWriter();

    try {
      template = configuration.getTemplate("svg-badge-template.ftl");
      template.process(templateData, writer);
    } catch (IOException | TemplateException e) {
      // TODO: Evaluate use of vavr
      e.printStackTrace();
    }
    state.setRenderedString(writer.toString());
  }
}
