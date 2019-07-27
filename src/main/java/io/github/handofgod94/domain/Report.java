package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;
import io.vavr.collection.List;


@AutoValue
public abstract class Report {
  public static final String CSV_EXT = "csv";

  public static Report create(List<ReportLine> lines) {
    return new AutoValue_Report(lines);
  }

  public abstract List<ReportLine> getLines();
}
