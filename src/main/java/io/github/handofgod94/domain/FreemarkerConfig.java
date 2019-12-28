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

import java.io.IOException;
import java.util.Locale;

public class FreemarkerConfig {

  public static final String DEFAULT_TEMPLATE = "svg-badge-template.ftl";

  private final Configuration configuration;

  public FreemarkerConfig() {
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

  public Template getDefaultTemplate() throws IOException {
    return configuration.getTemplate(DEFAULT_TEMPLATE);
  }
}
