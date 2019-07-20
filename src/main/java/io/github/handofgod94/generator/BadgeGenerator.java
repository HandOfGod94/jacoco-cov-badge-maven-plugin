package io.github.handofgod94.generator;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;

import java.io.File;

public class BadgeGenerator extends BaseBadgeGenerator {

  public static final String DEFAULT_BADGE_LABEL = "coverage";

  private final Badge.CoverageCategory category;
  private final String badgeLabel;
  private final File jacocoReportFile;
  private final File outputFile;

  public BadgeGenerator(Badge.CoverageCategory category, String badgeLabel, File jacocoReportFile, File outputFile) {
    this.category = category;
    this.badgeLabel = badgeLabel !=  null ? badgeLabel : DEFAULT_BADGE_LABEL;
    this.jacocoReportFile = jacocoReportFile;
    this.outputFile = outputFile;
  }

  public Badge execute() {
    Configuration configuration = initializeConfiguration();
    Coverage coverage = calculateCoverage(jacocoReportFile, category);
    Badge badge = initializeBadge(coverage, badgeLabel);
    String badgeString = renderBadgeString(configuration, badge);
    boolean isSaveSuccess = saveToFile(outputFile, badgeString);
    return isSaveSuccess ? badge : null;
  }
}
