package io.github.handofgod94.service;

import com.google.common.io.Files;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.BadgeTemplate;
import io.github.handofgod94.domain.MyMojoConfiguration;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.coverage.CoverageCategory;
import io.github.handofgod94.service.format.Formatter;
import io.github.handofgod94.service.format.FormatterFactory;
import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.io.File;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Success;

public class BadgeGenerator {

  private File outputFile;
  private Badge badge;

  private Lazy<BadgeTemplate> badgeTemplate = Lazy.of(BadgeTemplate::new);

  /**
   * Service to generate badges.
   *
   * @param myMojoConfig config params provided in pom file of user.
   */
  public BadgeGenerator(MyMojoConfiguration myMojoConfig) {
    CoverageCategory category = myMojoConfig.getCoverageCategory();
    String badgeLabel = myMojoConfig.getBadgeLabel();
    Report report = new CsvReportParser(myMojoConfig.getJacocoReportFile()).parse();

    this.outputFile = myMojoConfig.getOutputFile();
    this.badge = Badge.create(badgeLabel, report.getCoverageValueFor(category));
  }

  /**
   * Generates badge.
   * It calculates and renders the badge using the config values provided
   *
   * @return Instance of Badge, if rendering is success, Option.empty() otherwise.
   */
  public Option<Badge> generate() {
    Formatter formatter = FormatterFactory.createFormatter(fileExt());
    Try<Void> result = formatter.save(outputFile, renderBadge());

    return Match(result).option(
      Case($Success($()), () -> badge)
    );
  }

  private String renderBadge() {
    return badgeTemplate.get().render(badge);
  }

  private String fileExt() {
    return Files.getFileExtension(outputFile.getName());
  }
}
