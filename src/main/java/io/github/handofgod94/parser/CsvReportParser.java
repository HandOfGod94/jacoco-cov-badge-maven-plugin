package io.github.handofgod94.parser;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import io.github.handofgod94.domain.Report;
import io.github.handofgod94.domain.ReportLine;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.function.Function;

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

    return ReportLine.builder()
      .setGroupName(groupName).setPackageName(packageName).setClassName(className)
      .setInstructionMissed(instructionMissed)
      .setInstructionCovered(instructionCovered)
      .setBranchMissed(branchMissed)
      .setBranchCovered(branchCovered)
      .setLineMissed(lineMissed)
      .setLineCovered(lineCovered)
      .setComplexityCovered(complexityCovered)
      .setComplexityMissed(complexityMissed)
      .setMethodMissed(methodMissed)
      .setMethodCovered(methodCovered).build();
  };

  @Override
  public Report parseReport(File file) {
    List<ReportLine> report =
      Try
        .withResources(() -> createCsvReader(file))
        .of(CSVReader::readNext)
        .mapTry(csvLineToReportLineMapper::apply)
        .toList();

    return Report.create(report);
  }

  private CSVReader createCsvReader(File file) throws FileNotFoundException {
    return
      new CSVReaderBuilder(new FileReader(file))
        .withSkipLines(1)
        .build();
  }
}
