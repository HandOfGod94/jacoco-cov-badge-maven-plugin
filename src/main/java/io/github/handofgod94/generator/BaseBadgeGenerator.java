package io.github.handofgod94.generator;

import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.github.handofgod94.MyMojo;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.generator.helper.CoverageHelper;
import io.github.handofgod94.parser.ReportParser;
import io.github.handofgod94.parser.ReportParserFactory;

import java.io.*;
import java.util.Locale;

class BaseBadgeGenerator {

  private Configuration configuration;
  private Coverage coverage;

  void loadFreemarkerConfig() {
    configuration = new freemarker.template.Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    TemplateConfiguration tcSvg = new TemplateConfiguration();
    tcSvg.setOutputFormat(XMLOutputFormat.INSTANCE);

    configuration.setTemplateConfigurations(
      new ConditionalTemplateConfigurationFactory(new PathGlobMatcher("**/svg-*"), tcSvg));
  }

  void calculateCoverage(File jacocoReport, Badge.CoverageCategory category) throws IOException {
    ReportParser reportParser = ReportParserFactory.create(jacocoReport);
    Reader reader = new FileReader(jacocoReport);
    Report report = reportParser.parseReport(reader);
    CoverageHelper coverageHelper = new CoverageHelper(category, report);
    coverage = coverageHelper.loadCoverage();
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public Coverage getCoverage() {
    return coverage;
  }
}
