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

public class CsvReportParser implements ReportParser {

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
    String groupName = line[GROUP_COL_NO];
    String packageName = line[PACKAGE_COL_NO];
    String className = line[CLASS_COL_NO];
    int instructionMissed = Integer.parseInt(line[INSTRUCTION_MISSED_COL_NO]);
    int instructionCovered = Integer.parseInt(line[INSTRUCTION_COVERED_COL_NO]);
    int branchMissed = Integer.parseInt(line[BRANCH_MISSED_COL_NO]);
    int branchCovered = Integer.parseInt(line[BRANCH_COVERED_COL_NO]);
    int lineMissed = Integer.parseInt(line[LINE_MISSED_COL_NO]);
    int lineCovered = Integer.parseInt(line[LINE_COVERED_COL_NO]);
    int complexityMissed = Integer.parseInt(line[COMPLEXITY_MISSED_COL_NO]);
    int complexityCovered = Integer.parseInt(line[COMPLEXITY_COVERED_COL_NO]);
    int methodMissed = Integer.parseInt(line[METHOD_MISSED_COL_NO]);
    int methodCovered = Integer.parseInt(line[METHOD_COVERED_COL_NO]);

    return new ReportLineBuilder()
      .addGroupName(groupName).addPackageName(packageName).addClassName(className)
      .addInstructionMissed(instructionMissed)
      .addInstructionCovered(instructionCovered)
      .addBranchMissed(branchMissed)
      .addBranchCovered(branchCovered)
      .addLineMissed(lineMissed)
      .addLineCovered(lineCovered)
      .addComplexityCovered(complexityCovered)
      .addComplexityMissed(complexityMissed)
      .addMethodMissed(methodMissed)
      .addMethodCovered(methodCovered).build();
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
