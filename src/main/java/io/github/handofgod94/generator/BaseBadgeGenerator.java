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

  private Configuration configuration;
  private Coverage coverage;
  private Badge badge;
  private String processedTemplateString;

  void loadFreemarkerConfig() {
    Configuration configuration = new freemarker.template.Configuration(new Version(2, 3, 20));
    configuration.setClassForTemplateLoading(MyMojo.class, "templates");
    configuration.setDefaultEncoding("UTF-8");
    configuration.setLocale(Locale.US);
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    TemplateConfiguration tcSvg = new TemplateConfiguration();
    tcSvg.setOutputFormat(XMLOutputFormat.INSTANCE);

    configuration.setTemplateConfigurations(
      new ConditionalTemplateConfigurationFactory(new PathGlobMatcher("**/svg-*"), tcSvg));

    this.setConfiguration(configuration);
  }

  void calculateCoverage(File jacocoReport, Badge.CoverageCategory category) throws IOException {
    ReportParser reportParser = ReportParserFactory.create(jacocoReport);
    Reader reader = new FileReader(jacocoReport);
    Report report = reportParser.parseReport(reader);
    CoverageHelper coverageHelper = new CoverageHelper(category, report);

    this.setCoverage(coverageHelper.loadCoverage());
  }

  void generateBadgeData(String badgeLabel) {
    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = new Badge(badgeLabel, badgeValue);

    this.setBadge(badge);
  }

  void processSvgBadgeTemplate() throws IOException, TemplateException {
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("badge", badge);

    Writer writer = new StringWriter();
    Template template = configuration.getTemplate("svg-badge-template.ftl");
    template.process(templateData, writer);

    this.setProcessedTemplateString(writer.toString());
  }

  void save(File outputFile) throws IOException, TranscoderException {
    String fileExt = BadgeUtility.getFileExt(outputFile).orElseThrow(()-> new IllegalArgumentException("Invalid output file provided"));
    Formatter formatter = FormatterFactory.createFormatter(fileExt);
    formatter.save(outputFile, processedTemplateString);
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public void setCoverage(Coverage coverage) {
    this.coverage = coverage;
  }

  public void setBadge(Badge badge) {
    this.badge = badge;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public Coverage getCoverage() {
    return coverage;
  }

  public Badge getBadge() {
    return badge;
  }

  public String getProcessedTemplateString() {
    return processedTemplateString;
  }

  public void setProcessedTemplateString(String processedTemplateString) {
    this.processedTemplateString = processedTemplateString;
  }
}
