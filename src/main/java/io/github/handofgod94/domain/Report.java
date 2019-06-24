package io.github.handofgod94.domain;

import java.util.List;

public class Report {
  public static final String CSV_EXT = "csv";

  private final List<ReportLine> lines;

  public Report(List<ReportLine> lines) {
    this.lines = lines;
  }

  public List<ReportLine> getLines() {
    return this.lines;
  }
}
