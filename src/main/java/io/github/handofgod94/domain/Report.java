package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import io.github.handofgod94.domain.coverage.Coverage;
import io.vavr.collection.List;


@AutoValue
public abstract class Report {

  public static Report create(List<ReportLine> lines) {
    return new AutoValue_Report(lines);
  }

  public Coverage getCoverage(Coverage.CoverageCategory category) {
    return Coverage.create(category, this);
  }

  public abstract List<ReportLine> getLines();
}
