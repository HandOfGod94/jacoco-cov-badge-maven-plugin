package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.domain.coverage.CoverageCategory;
import io.vavr.collection.List;


@AutoValue
public abstract class Report {

  public static Report create(List<ReportLine> lines) {
    return new AutoValue_Report(lines);
  }

  public abstract List<ReportLine> getLines();

  public int getCoverageValueFor(CoverageCategory category) {
    Coverage coverage = Coverage.create(category, this);
    return (int) Math.floor(coverage.getCoveragePercentage());
  }
}
