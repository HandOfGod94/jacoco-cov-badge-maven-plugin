package io.github.handofgod94.service;

import io.github.handofgod94.BadgeUtility;
import io.github.handofgod94.domain.*;
import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.service.format.Formatter;
import io.github.handofgod94.service.format.FormatterFactory;
import io.github.handofgod94.service.parser.ReportParserFactory;
import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.File;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Success;

public class BadgeGenerator {

  private CoverageCategory category;
  private String badgeLabel;
  private File jacocoReportFile;
  private File outputFile;

  private Lazy<BadgeTemplate> badgeTemplate = Lazy.of(BadgeTemplate::new);

  private Lazy<Badge> badge = Lazy.of(() -> Badge.create(badgeLabel, badgeValue()));

  /**
   * Service to generate badges.
   *
   * @param myMojoConfig config params provided in pom file of user.
   */
  public BadgeGenerator(MyMojoConfiguration myMojoConfig) {
    this.category = myMojoConfig.getCoverageCategory();
    this.badgeLabel = myMojoConfig.getBadgeLabel();
    this.jacocoReportFile = myMojoConfig.getJacocoReportFile();
    this.outputFile = myMojoConfig.getOutputFile();
  }

  /**
   * Generates badge.
   * It calculates and renders the badge using the config values provided
   *
   * @return Instance of Badge, if rendering is success, Option.empty() otherwise.
   */
  public Option<Badge> generate() {
    Formatter formatter = FormatterFactory.createFormatter(fileExt());
    Try<Void> result = formatter.save(outputFile, generateBadgeString());

    return Match(result).option(
      Case($Success($()), () -> badge.get())
    );
  }

  private Coverage coverage() {
    return report().getCoverage(category);
  }

  private int badgeValue() {
    return (int) Math.floor(coverage().getCoveragePercentage());
  }

  private Report report() {
    return ReportParserFactory.create(jacocoReportFile)
      .parseReport(jacocoReportFile);
  }

  private String generateBadgeString() {
    return badgeTemplate.get().render(badge.get());
  }

  private String fileExt() {
    return BadgeUtility.getFileExt(outputFile)
      .orElseThrow(() -> new IllegalArgumentException("Invalid output file provided"));
  }
}
