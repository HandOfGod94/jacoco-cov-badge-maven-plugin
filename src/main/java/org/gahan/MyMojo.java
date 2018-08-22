package org.gahan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

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
   * Default: <b>
   */
  @Parameter(property = "badge.jacocoReportLocation", defaultValue = "${project.reporting.outputDirectory}/jacoco")
  private File jacocoReportConfig;

  @Override
  public void execute() throws MojoExecutionException {
    try {
      String csvPath = Paths.get(jacocoReportConfig.getAbsolutePath(), "jacoco.csv").toString();
      int badgeValue = BadgeUtility.calculateCoverate(csvPath);
      Badge badge = new Badge(badgeLabel, resultColor, badgeValue);
      getLog().info("Total Coverage from Gahan's Plugin:" + badge.getBadgeValue());
    } catch (IOException ex) {
      getLog().error("Unable to generate badge", ex);
    }
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
