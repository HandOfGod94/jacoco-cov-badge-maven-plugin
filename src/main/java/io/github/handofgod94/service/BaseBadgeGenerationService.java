package io.github.handofgod94.service;

import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.github.handofgod94.BadgeUtility;
import io.github.handofgod94.MyMojo;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.format.Formatter;
import io.github.handofgod94.format.FormatterFactory;
import io.github.handofgod94.parser.ReportParser;
import io.github.handofgod94.parser.ReportParserFactory;
import io.github.handofgod94.service.helper.CoverageHelper;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

class BaseBadgeGenerationService {

  protected Configuration initializeConfiguration() {
    Configuration configuration = new Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    TemplateConfiguration tcSvg = new TemplateConfiguration();
    tcSvg.setOutputFormat(XMLOutputFormat.INSTANCE);

    configuration.setTemplateConfigurations(
        new ConditionalTemplateConfigurationFactory(new PathGlobMatcher("**/svg-*"), tcSvg));

    return configuration;
  }

  protected Coverage calculateCoverage(File jacocoReport, Badge.CoverageCategory category) {
    ReportParser reportParser = ReportParserFactory.create(jacocoReport);
    Report report = Try.of(() -> reportParser.parseReport(new FileReader(jacocoReport)))
                        .getOrElse(() -> Report.create(List.empty()));

    CoverageHelper coverageHelper = new CoverageHelper(category, report);
    return coverageHelper.loadCoverage();
  }

  protected Badge initializeBadge(Coverage coverage, String badgeLabel) {
    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = Badge.create(badgeLabel, badgeValue);

    return badge;
  }

  protected Try<Void> saveToFile(File outputFile, String renderedString) {
    String fileExt =
        BadgeUtility.getFileExt(outputFile)
            .orElseThrow(() -> new IllegalArgumentException("Invalid output file provided"));
    Formatter formatter = FormatterFactory.createFormatter(fileExt);
    return formatter.save(outputFile, renderedString);
  }

}
