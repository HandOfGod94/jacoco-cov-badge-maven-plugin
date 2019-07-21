package io.github.handofgod94.domain;

import java.util.Objects;

/**
 * Domain representing a line in jacoco report.
 * Different jacoco report formats will have different ways of representing a report.
 * For e.g. CSV Report, each entity is separated by commas,
 * while in Lcov report it will be different.
 * All of them represents same thing.
 */
public class ReportLine {

  private final String groupName;
  private final String packageName;
  private final String className;
  private final long instructionMissed;
  private final long instructionCovered;
  private final long branchMissed;
  private final long branchCovered;
  private final long lineMissed;
  private final long lineCovered;
  private final long complexityCovered;
  private final long complexityMissed;
  private final long methodMissed;
  private final long methodCovered;


  ReportLine(String groupName,
             String packageName,
             String className,
             long instructionMissed, long instructionCovered,
             long branchMissed, long branchCovered,
             long lineMissed, long lineCovered,
             long complexityMissed, long complexityCovered,
             long methodMissed, long methodCovered) {
    this.groupName = groupName;
    this.packageName = packageName;
    this.className = className;
    this.instructionMissed = instructionMissed;
    this.instructionCovered = instructionCovered;
    this.branchMissed = branchMissed;
    this.branchCovered = branchCovered;
    this.lineMissed = lineMissed;
    this.lineCovered = lineCovered;
    this.complexityCovered = complexityCovered;
    this.complexityMissed = complexityMissed;
    this.methodMissed = methodMissed;
    this.methodCovered = methodCovered;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getClassName() {
    return className;
  }

  public long getInstructionMissed() {
    return instructionMissed;
  }

  public long getInstructionCovered() {
    return instructionCovered;
  }

  public long getBranchMissed() {
    return branchMissed;
  }

  public long getBranchCovered() {
    return branchCovered;
  }

  public long getLineMissed() {
    return lineMissed;
  }

  public long getLineCovered() {
    return lineCovered;
  }

  public long getComplexityCovered() {
    return complexityCovered;
  }

  public long getComplexityMissed() {
    return complexityMissed;
  }

  public long getMethodMissed() {
    return methodMissed;
  }

  public long getMethodCovered() {
    return methodCovered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReportLine that = (ReportLine) o;
    return instructionMissed == that.instructionMissed
        && instructionCovered == that.instructionCovered
        && branchMissed == that.branchMissed
        && branchCovered == that.branchCovered
        && lineMissed == that.lineMissed
        && lineCovered == that.lineCovered
        && complexityCovered == that.complexityCovered
        && complexityMissed == that.complexityMissed
        && methodMissed == that.methodMissed
        && methodCovered == that.methodCovered
        && Objects.equals(groupName, that.groupName)
        && Objects.equals(packageName, that.packageName)
        && Objects.equals(className, that.className);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, packageName, className,
        instructionMissed,instructionCovered,
        branchMissed, branchCovered,
        lineMissed, lineCovered,
        complexityCovered, complexityMissed,
        methodMissed, methodCovered);
  }
}
