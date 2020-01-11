package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import io.github.handofgod94.domain.coverage.Coverage;

import java.io.File;

@AutoValue
public abstract class MyMojoConfiguration {
  public static final String DEFAULT_BADGE_LABEL = "coverage";


  public abstract String getBadgeLabel();
  public abstract File getJacocoReportFile();
  public abstract File getOutputFile();
  public abstract CoverageCategory getCoverageCategory();


  public static Builder builder() {
    return new AutoValue_MyMojoConfiguration.Builder()
      .setBadgeLabel(DEFAULT_BADGE_LABEL);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setBadgeLabel(String badgeLabel);
    public abstract Builder setJacocoReportFile(File jacocoReportFile);
    public abstract Builder setOutputFile(File outputFile);
    public abstract Builder setCoverageCategory(CoverageCategory category);
    public abstract MyMojoConfiguration build();
  }

}
