package org.gahan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

/**
 * Goal to generate badge during the build
 *
 * @phase verify
 */
@Mojo(name = "badge")
public class MyMojo extends AbstractMavenReport {

  /**
   * Label for the badge
   * <p>
   * Default: <b>coverage</b>
   * </p>
   */
  @Parameter(property = "badge.badgeLabel", defaultValue = "coverage")
  private String badgeLabel;

  /**
   * Color for the result
   * <p>
   * Default: <b>#4c1</b>
   * </p>
   */
  @Parameter(property = "badge.resultColor", defaultValue = "#4c1")
  private String resultColor;

  /**
   * Jacoco Reprot file location.
   * <p>
   * Default: <b>#4c1</b>
   */
  @Parameter(property = "badge.jacocoReportLocation", defaultValue = "${project.reporting.outputDirectory}/jacoco/jacoco.csv")
  private File jacocoReportConfig;

  @Parameter(property = "badge.outputFile", defaultValue = "${project.build.directory}/coverage.svg")
  private File outputFile;

  @Override
  public void execute() throws MojoExecutionException {
    try {
      int badgeValue = BadgeUtility.calculateCoverage(jacocoReportConfig.getAbsolutePath());
      Badge badge = new Badge(badgeLabel, resultColor, badgeValue);
      getLog().info("Total Coverage from Gahan's Plugin:" + badge.getBadgeValue());
      getLog().debug("Trying to render badge");
      renderBadge(badge);
    } catch (IOException | TemplateException ex) {
      getLog().error("Unable to generate badge", ex);
    }
  }

  /**
   * Renders svg badge on configuration provided by user
   *
   * @param badge Badge
   * @throws IOException
   * @throws ParseException
   * @throws MalformedTemplateNameException
   * @throws TemplateNotFoundException
   * @throws TemplateException
   */
  private void renderBadge(Badge badge) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
    // configure freemarker
    Configuration configuration = new Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    // load the template
    Template template = configuration.getTemplate("svg-badge-template.ftl");

    // map object for generating tempalte
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    // Write to file
    outputFile = new File(outputFile.getAbsolutePath());
    Writer writer = new FileWriter(outputFile);
    template.process(templateData, writer);

  }

  @Override
  public String getOutputName() {
    return null;
  }

  @Override
  public String getName(Locale locale) {
    return null;
  }

  @Override
  public String getDescription(Locale locale) {
    return null;
  }

  @Override
  protected void executeReport(Locale locale) throws MavenReportException {

  }

}
