package io.github.handofgod94.generator;

import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.*;
import io.github.handofgod94.BadgeUtility;
import io.github.handofgod94.MyMojo;
import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.format.Formatter;
import io.github.handofgod94.format.FormatterFactory;
import io.github.handofgod94.generator.helper.CoverageHelper;
import io.github.handofgod94.parser.ReportParser;
import io.github.handofgod94.parser.ReportParserFactory;
import org.apache.batik.transcoder.TranscoderException;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class BaseBadgeGenerator {

  Configuration initializeConfiguration() {
    Configuration configuration = new freemarker.template.Configuration(new Version(2, 3, 20));
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

  Coverage calculateCoverage(File jacocoReport, Badge.CoverageCategory category) {
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
    return coverageHelper.loadCoverage();
  }

  Badge initializeBadge(Coverage coverage, String badgeLabel) {
    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = new Badge(badgeLabel, badgeValue);

    return badge;
  }

  String renderBadgeString(Configuration configuration, Badge badge) {
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    Template template = null;
    Writer writer = new StringWriter();

    try {
      template = configuration.getTemplate("svg-badge-template.ftl");
      template.process(templateData, writer);
    } catch (IOException | TemplateException e) {
      // TODO: Evaluate use of vavr
      e.printStackTrace();
    }

    return writer.toString();
  }

  boolean saveToFile(File outputFile, String renderedString) {
    try {
      String fileExt = BadgeUtility.getFileExt(outputFile).orElseThrow(()-> new IllegalArgumentException("Invalid output file provided"));
      Formatter formatter = FormatterFactory.createFormatter(fileExt);
      formatter.save(outputFile, renderedString);
    } catch (IOException | TranscoderException e) {
      e.printStackTrace();
      // TODO: evalute use of vavr
      return false;
    }

    return true;
  }

}
