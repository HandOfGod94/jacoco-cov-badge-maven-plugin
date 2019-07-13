package io.github.handofgod94.generator;

import freemarker.template.Configuration;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;

import java.io.File;

public class BadgeProcessState {
  private Badge badge;
  private Coverage coverage;
  private Configuration configuration;
  private String renderedString;
  private File jacocoReport;
  private Badge.CoverageCategory category;
  private String badgeLabel;
  private File outputFile;

  public BadgeProcessState(File jacocoReport, File outputFile, Badge.CoverageCategory category, String badgeLabel) {
    this.jacocoReport = jacocoReport;
    this.category = category;
    this.badgeLabel = badgeLabel;
    this.outputFile = outputFile;
  }

  public Badge getBadge() {
    return badge;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  public Coverage getCoverage() {
    return coverage;
  }

  public void setCoverage(Coverage coverage) {
    this.coverage = coverage;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public String getRenderedString() {
    return renderedString;
  }

  public void setRenderedString(String renderedString) {
    this.renderedString = renderedString;
  }

  public File getJacocoReport() {
    return jacocoReport;
  }

  public Badge.CoverageCategory getCategory() {
    return category;
  }

  public String getBadgeLabel() {
    return badgeLabel;
  }

  public File getOutputFile() {
    return outputFile;
  }
}
