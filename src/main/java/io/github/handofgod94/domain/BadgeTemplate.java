package io.github.handofgod94.domain;

import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.github.handofgod94.MyMojo;
import io.vavr.control.Try;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

public class BadgeTemplate {

  public static final String DEFAULT_TEMPLATE = "svg-badge-template.ftl";

  private Configuration configuration;

  /**
   * Initializes badge template with freemarker config.
   */
  public BadgeTemplate() {
    initializeConfiguration();
  }

  private void initializeConfiguration() {
    configuration = new Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    TemplateConfiguration tcSvg = new TemplateConfiguration();
    tcSvg.setOutputFormat(XMLOutputFormat.INSTANCE);

    configuration.setTemplateConfigurations(
      new ConditionalTemplateConfigurationFactory(new PathGlobMatcher("**/svg-*"), tcSvg));
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * Renders a badge based with default template.
   *
   * @param badge a valid badge with all the data
   * @return string for Success case or empty string if exception occurs while processing string.
   */
  public String render(Badge badge) {
    return
      Try
        .withResources(StringWriter::new)
        .of(templateWriter -> {
          getDefaultTemplate().process(badge.templateData(), templateWriter);
          return templateWriter.toString();
        })
        .getOrElse("");
  }

  private Template getDefaultTemplate() throws IOException {
    return configuration.getTemplate(DEFAULT_TEMPLATE);
  }
}
