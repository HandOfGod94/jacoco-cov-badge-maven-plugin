package io.github.handofgod94.service;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.coverage.Coverage;
import io.github.handofgod94.parser.ReportParser;
import io.github.handofgod94.parser.ReportParserFactory;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.io.File;
import java.io.FileReader;

@Deprecated
class BaseBadgeGenerationService {

  protected Coverage calculateCoverage(File jacocoReport, Coverage.CoverageCategory category) {
    ReportParser reportParser = ReportParserFactory.create(jacocoReport);
    Report report = Try.of(() -> reportParser.parseReport(new FileReader(jacocoReport)))
        .getOrElse(() -> Report.create(List.empty()));

    Coverage coverage = Coverage.create(category, report);
    return coverage;
  }
  protected Badge initializeBadge(Coverage coverage, String badgeLabel) {
    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = Badge.create(badgeLabel, badgeValue);

    return badge;
  }

}
