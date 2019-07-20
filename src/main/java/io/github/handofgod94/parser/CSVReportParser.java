package io.github.handofgod94.parser;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.github.handofgod94.domain.ReportLineBuilder;
import io.vavr.control.Try;

import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVReportParser implements ReportParser {

  // jacoco csv report column number
  public static final int GROUP_COL_NO = 0;
  public static final int PACKAGE_COL_NO = 1;
  public static final int CLASS_COL_NO = 2;
  public static final int INSTRUCTION_MISSED_COL_NO = 3;
  public static final int INSTRUCTION_COVERED_COL_NO = 4;
  public static final int BRANCH_MISSED_COL_NO = 5;
  public static final int BRANCH_COVERED_COL_NO = 6;
  public static final int LINE_MISSED_COL_NO = 7;
  public static final int LINE_COVERED_COL_NO = 8;
  public static final int COMPLEXITY_MISSED_COL_NO = 9;
  public static final int COMPLEXITY_COVERED_COL_NO = 10;
  public static final int METHOD_MISSED_COL_NO = 11;
  public static final int METHOD_COVERED_COL_NO = 12;

  private final Function<String[], ReportLine> csvLineToReportLineMapper = line -> {
    String jGroup = line[GROUP_COL_NO];
    String jPackage = line[PACKAGE_COL_NO];
    String jClass = line[CLASS_COL_NO];
    int jInstructionMissed = Integer.parseInt(line[INSTRUCTION_MISSED_COL_NO]);
    int jInstructionCovered = Integer.parseInt(line[INSTRUCTION_COVERED_COL_NO]);
    int jBranchMissed = Integer.parseInt(line[BRANCH_MISSED_COL_NO]);
    int jBranchCovered = Integer.parseInt(line[BRANCH_COVERED_COL_NO]);
    int jLineMissed = Integer.parseInt(line[LINE_MISSED_COL_NO]);
    int jLineCovered = Integer.parseInt(line[LINE_COVERED_COL_NO]);
    int jComplexityMissed = Integer.parseInt(line[COMPLEXITY_MISSED_COL_NO]);
    int jComplexityCovered = Integer.parseInt(line[COMPLEXITY_COVERED_COL_NO]);
    int jMethodMissed = Integer.parseInt(line[METHOD_MISSED_COL_NO]);
    int jMethodCovered = Integer.parseInt(line[METHOD_COVERED_COL_NO]);

    return new ReportLineBuilder()
      .addGroup(jGroup).addPackage(jPackage).addClass(jClass)
      .addJInstructionMissed(jInstructionMissed)
      .addJInstructionCovered(jInstructionCovered)
      .addJBranchMissed(jBranchMissed)
      .addJBranchCovered(jBranchCovered)
      .addJLineMissed(jLineMissed)
      .addJLineCovered(jLineCovered)
      .addJComplexityCovered(jComplexityCovered)
      .addJComplexityMissed(jComplexityMissed)
      .addJMethodMissed(jMethodMissed)
      .addJMethodCovered(jMethodCovered).build();
  };

  @Override
  public Report parseReport(Reader reader) {
    CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

    List<ReportLine> report = Try.of(csvReader::readNext)
      .toStream()
      .map(csvLineToReportLineMapper)
      .collect(Collectors.toList());

    return new Report(report);
  }
}
