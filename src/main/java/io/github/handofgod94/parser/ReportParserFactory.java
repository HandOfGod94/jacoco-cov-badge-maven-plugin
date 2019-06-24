package io.github.handofgod94.parser;

import io.github.handofgod94.BadgeUtility;
import io.github.handofgod94.domain.Report;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;

public class ReportParserFactory {

  public static ReportParser create(File file) {
    String ext = BadgeUtility.getFileExt(file).orElseThrow(() -> new IllegalArgumentException("Invalid Jacoco file provided"));

    if (ext.equals(Report.CSV_EXT)) {
      return new CSVReportParser();
    } else {
      throw new NotImplementedException("Report extension is not yet parsable");
    }
  }
}
