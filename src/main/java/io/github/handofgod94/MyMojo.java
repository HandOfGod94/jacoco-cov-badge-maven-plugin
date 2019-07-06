package io.github.handofgod94;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.format.Formatter;
import io.github.handofgod94.format.FormatterFactory;

/**
 * Goal to generate badge during the build.
 *
 * <p>Default Phase: Verify
 */
@Mojo(name = "badge", defaultPhase = LifecyclePhase.VERIFY)
public class MyMojo extends AbstractMojo {

  @Parameter(property = "badge.badgeLabel", defaultValue = "coverage")
  private String badgeLabel;

  @Parameter(property = "badge.jacocoReportLocation",
      defaultValue = "${project.reporting.outputDirectory}/jacoco/jacoco.csv")
  private File jacocoReportConfig;

  @Parameter(property = "badge.outputFile",
      defaultValue = "${project.build.directory}/coverage.svg")
  private File outputFile;

  @Parameter(property = "badge.coverageCategory", defaultValue = "INSTRUCTION")
  private Badge.CoverageCategory coverageCategory;

  @Override
  public void execute() throws MojoExecutionException {
    try {

      int badgeValue = BadgeUtility.calculateCoverage(jacocoReportConfig.getAbsolutePath(),
                                                      coverageCategory);
      Badge badge = new Badge(badgeLabel, badgeValue);

      getLog().info("Total Coverage calculated by badge plugin:" + badge.getBadgeValue());
      renderBadge(badge);
    } catch (IOException | TemplateException ex) {
      getLog().error("Unable to generate badge", ex);
    } catch (TranscoderException ex) {
      getLog().error("Unable to generate badge in specified format", ex);
    }
  }

  private void renderBadge(Badge badge) throws IOException, TemplateException, TranscoderException {
    // configure freemarker
    Configuration configuration = new Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    TemplateConfiguration tcSvg = new TemplateConfiguration();
    tcSvg.setOutputFormat(XMLOutputFormat.INSTANCE);

    configuration.setTemplateConfigurations(
        new ConditionalTemplateConfigurationFactory(new PathGlobMatcher("**/svg-*"), tcSvg));

    // load the template
    Template template = configuration.getTemplate("svg-badge-template.ftl");

    // map object for generating template
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    // Write to file
    outputFile = new File(outputFile.getAbsolutePath());
    StringWriter writer = new StringWriter();
    template.process(templateData, writer);
    String fileExt = BadgeUtility.getFileExt(outputFile)
                        .orElseThrow(()-> new IllegalArgumentException("Invalid output file provided"));
    Formatter formatter = FormatterFactory.createFormatter(fileExt);
    formatter.save(outputFile, writer.toString());
  }

}
