package io.github.handofgod94;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.generator.BadgeGenerator;
import io.github.handofgod94.generator.BadgeProcessState;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

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
  private File jacocoReportFile;

  @Parameter(property = "badge.outputFile",
      defaultValue = "${project.build.directory}/coverage.svg")
  private File outputFile;

  @Parameter(property = "badge.coverageCategory", defaultValue = "INSTRUCTION")
  private Badge.CoverageCategory coverageCategory;

  @Override
  public void execute() throws MojoExecutionException {
    BadgeGenerator generator = new BadgeGenerator(coverageCategory, badgeLabel, jacocoReportFile, outputFile);
    Badge badge = generator.execute();
    getLog().info("Total Coverage calculated by badge plugin:" + badge.getBadgeValue());
  }

}
