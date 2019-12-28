package io.github.handofgod94.service;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.File;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

public class BadgeGenerationService extends BaseBadgeGenerationService {

  public static final String DEFAULT_BADGE_LABEL = "coverage";

  private final Badge.CoverageCategory category;
  private final String badgeLabel;
  private final File jacocoReportFile;
  private final File outputFile;

  public BadgeGenerationService(MyMojoConfiguration myMojoConfig) {
    this.category = myMojoConfig.getCoverageCategory();
    this.badgeLabel = myMojoConfig.getBadgeLabel();
    this.jacocoReportFile = myMojoConfig.getJacocoReportFile();
    this.outputFile = myMojoConfig.getOutputFile();
  }

  /**
   * Generates badge.
   * It calculates and renders the badge using the config values provided
   * @return Instance of Badge, if rendering is success, Option.empty() otherwise.
   */
  public Option<Badge> generate() {
    Configuration configuration = initializeConfiguration();
    Coverage coverage = calculateCoverage(jacocoReportFile, category);
    Badge badge = initializeBadge(coverage, badgeLabel);

    String badgeString = Match(renderBadgeString(configuration, badge)).of(
        Case($Success($()), str -> str),
        Case($Failure($()), "")
    );

    Try<Void> result = saveToFile(outputFile, badgeString);

    return Match(result).option(
      Case($Success($()), () -> badge)
    );
  }
}
