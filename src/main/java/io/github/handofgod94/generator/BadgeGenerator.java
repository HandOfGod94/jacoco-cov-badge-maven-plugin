package io.github.handofgod94.generator;

import freemarker.template.TemplateException;
import io.github.handofgod94.domain.Badge;
import org.apache.batik.transcoder.TranscoderException;

import java.io.File;
import java.io.IOException;

public class BadgeGenerator extends BaseBadgeGenerator {

  public static final String DEFAULT_BADGE_LABEL = "coverage";

  private final Badge.CoverageCategory category;
  private final String badgeLabel;
  private final File jacocoReportFile;
  private final File badgeFile;

  public BadgeGenerator(Badge.CoverageCategory category, String badgeLabel, File jacocoReportFile, File badgeFile) {
    this.category = category;
    this.badgeLabel = badgeLabel !=  null ? badgeLabel : DEFAULT_BADGE_LABEL;
    this.jacocoReportFile = jacocoReportFile;
    this.badgeFile = badgeFile;
  }

  public void execute() throws IOException, TemplateException, TranscoderException {
    loadFreemarkerConfig();
    calculateCoverage(jacocoReportFile, category);
    generateBadgeData(badgeLabel);
    processSvgBadgeTemplate();
    save(badgeFile);
  }
}
