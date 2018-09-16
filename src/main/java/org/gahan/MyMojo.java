package org.gahan;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal to generate badge during the build.
 *
 * @phase verify
 */
@Mojo(name = "badge")
public class MyMojo extends AbstractMojo {

  @Parameter(property = "badge.badgeLabel", defaultValue = "coverage")
  private String badgeLabel;

  @Parameter(property = "badge.jacocoReportLocation",
      defaultValue = "${project.reporting.outputDirectory}/jacoco/jacoco.csv")
  private File jacocoReportConfig;

  @Parameter(property = "badge.outputFile",
      defaultValue = "${project.build.directory}/coverage.svg")
  private File outputFile;

  @Override
  public void execute() throws MojoExecutionException {
    try {
      int badgeValue = BadgeUtility.calculateCoverage(jacocoReportConfig.getAbsolutePath());
      Badge badge = new Badge(badgeLabel, badgeValue);

      // TODO: Remove this from release version
      getLog().info("Total Coverage from Gahan's Plugin:" + badge.getBadgeValue());
      getLog().debug("Trying to render badge");
      renderBadge(badge);
    } catch (IOException | TemplateException ex) {
      getLog().error("Unable to generate badge", ex);
    }
  }

  /**
   * Renders svg badge on configuration provided by user.
   *
   * @param badge Badge
   * @throws IOException       Unable to read freemarker template
   * @throws TemplateException General exception occurred during processing of template
   */
  private void renderBadge(Badge badge) throws IOException, TemplateException {
    // configure freemarker
    Configuration configuration = new Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    // load the template
    Template template = configuration.getTemplate("svg-badge-template.ftl");

    // map object for generating template
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    // Write to file
    outputFile = new File(outputFile.getAbsolutePath());
    Writer writer = new FileWriter(outputFile);
    template.process(templateData, writer);

  }

}
