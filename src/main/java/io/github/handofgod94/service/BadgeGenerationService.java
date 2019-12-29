package io.github.handofgod94.service;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.domain.FreemarkerConfig;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;

public class BadgeGenerationService extends BaseBadgeGenerationService {

  public static final String DEFAULT_BADGE_LABEL = "coverage";

  private Coverage.CoverageCategory category;
  private String badgeLabel;
  private File jacocoReportFile;
  private File outputFile;
  private Writer templateWriter;

  private Lazy<FreemarkerConfig> freemarkerConfig = Lazy.of(() -> new FreemarkerConfig());
  private Lazy<Badge> badge = Lazy.of(() -> {
    Coverage coverage = calculateCoverage(jacocoReportFile, category);
    Badge badge = initializeBadge(coverage, badgeLabel);
    return badge;
  });

  /**
   * Service to generate badges.
   * @param myMojoConfig config params provided in pom file of user.
   */
  public BadgeGenerationService(MyMojoConfiguration myMojoConfig) {
    this.category = myMojoConfig.getCoverageCategory();
    this.badgeLabel = myMojoConfig.getBadgeLabel();
    this.jacocoReportFile = myMojoConfig.getJacocoReportFile();
    this.outputFile = myMojoConfig.getOutputFile();
    this.templateWriter = new StringWriter();
  }

  /**
   * Generates badge.
   * It calculates and renders the badge using the config values provided
   * @return Instance of Badge, if rendering is success, Option.empty() otherwise.
   */
  public Option<Badge> generate() {
    Try<Void> result = saveToFile(outputFile, generateBadgeString());

    return Match(result).option(
      Case($Success($()), () -> badge.get())
    );
  }

  public String generateBadgeString() {
    return Match(renderBadgeString(badge.get())).of(
      Case($Success($()), str -> str),
      Case($Failure($()), "")
    );
  }

  protected Try<String> renderBadgeString(Badge badge) {
    return Try.of(() -> freemarkerConfig.get().getDefaultTemplate())
      .andThenTry(template -> template.process(badge.templateData(), templateWriter))
      .mapTry(ignoreIfError -> templateWriter.toString());
  }
}
