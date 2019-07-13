package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.generator.BadgeProcessState;
import io.github.handofgod94.generator.helper.CoverageHelper;
import io.github.handofgod94.parser.ReportParser;
import io.github.handofgod94.parser.ReportParserFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CalculateCoverageTask implements Task {
  @Override
  public void perform(BadgeProcessState state) {
    if (state.getJacocoReport() == null) throw new IllegalStateException("Invalid jacoco report provided");
    if (state.getCategory() == null) throw new IllegalStateException("Invalid coverage category provided");

    File jacocoReport = state.getJacocoReport();
    Badge.CoverageCategory category = state.getCategory();

    ReportParser reportParser = ReportParserFactory.create(jacocoReport);
    Reader reader;
    Report report = null;
    try {
      reader = new FileReader(jacocoReport);
      report = reportParser.parseReport(reader);
    } catch (IOException e) {
      // do something meaningful.
      // TODO: evaluate use of vavr
      e.printStackTrace();
    }
    CoverageHelper coverageHelper = new CoverageHelper(category, report);
    state.setCoverage(coverageHelper.loadCoverage());
  }
}
