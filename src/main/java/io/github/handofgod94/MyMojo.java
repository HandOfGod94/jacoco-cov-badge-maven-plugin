package io.github.handofgod94;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.github.handofgod94.service.BadgeGenerationService;
import io.vavr.Lazy;
import io.vavr.control.Option;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;

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
  private Coverage.CoverageCategory coverageCategory;

  private Lazy<MyMojoConfiguration> myMojoConfig = Lazy.of(() -> MyMojoConfiguration.builder()
      .setBadgeLabel(badgeLabel)
      .setJacocoReportFile(jacocoReportFile)
      .setOutputFile(outputFile)
      .setCoverageCategory(coverageCategory)
      .build());

  @Override
  public void execute() {
    BadgeGenerationService generationService = new BadgeGenerationService(myMojoConfig.get());
    Option<Badge> badge = generationService.generate();
    getLog().info(buildMessage(badge));
  }

  private String buildMessage(Option<Badge> badge) {
    return Match(badge).of(
      Case($Some($()), b -> String.format("Total Coverage calculated by badge plugin: %s",
        b.getBadgeValue())),
      Case($None(), () -> "Could not create badge, please verify config")
    );
  }
}
