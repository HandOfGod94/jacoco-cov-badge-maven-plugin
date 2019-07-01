package io.github.handofgod94.domain;


import java.util.Objects;

/**
 * Represents each line in jacoco report
 */
public class ReportLine {

  private final String jGroup;
  private final String jPackage;
  private final String jClass;
  private final long jInstructionMissed;
  private final long jInstructionCovered;
  private final long jBranchMissed;
  private final long jBranchCovered;
  private final long jLineMissed;
  private final long jLineCovered;
  private final long jComplexityCovered;
  private final long jComplexityMissed;
  private final long jMethodMissed;
  private final long jMethodCovered;


  ReportLine(String jGroup,
             String jPackage,
             String jClass,
             long jInstructionMissed, long jInstructionCovered,
             long jBranchMissed, long jBranchCovered,
             long jLineMissed, long jLineCovered,
             long jComplexityMissed, long jComplexityCovered,
             long jMethodMissed, long jMethodCovered) {
    this.jGroup = jGroup;
    this.jPackage = jPackage;
    this.jClass = jClass;
    this.jInstructionMissed = jInstructionMissed;
    this.jInstructionCovered = jInstructionCovered;
    this.jBranchMissed = jBranchMissed;
    this.jBranchCovered = jBranchCovered;
    this.jLineMissed = jLineMissed;
    this.jLineCovered = jLineCovered;
    this.jComplexityCovered = jComplexityCovered;
    this.jComplexityMissed = jComplexityMissed;
    this.jMethodMissed = jMethodMissed;
    this.jMethodCovered = jMethodCovered;
  }

  public String getjGroup() {
    return jGroup;
  }

  public String getjPackage() {
    return jPackage;
  }

  public String getjClass() {
    return jClass;
  }

  public long getjInstructionMissed() {
    return jInstructionMissed;
  }

  public long getjInstructionCovered() {
    return jInstructionCovered;
  }

  public long getjBranchMissed() {
    return jBranchMissed;
  }

  public long getjBranchCovered() {
    return jBranchCovered;
  }

  public long getjLineMissed() {
    return jLineMissed;
  }

  public long getjLineCovered() {
    return jLineCovered;
  }

  public long getjComplexityCovered() {
    return jComplexityCovered;
  }

  public long getjComplexityMissed() {
    return jComplexityMissed;
  }

  public long getjMethodMissed() {
    return jMethodMissed;
  }

  public long getjMethodCovered() {
    return jMethodCovered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReportLine that = (ReportLine) o;
    return jInstructionMissed == that.jInstructionMissed &&
      jInstructionCovered == that.jInstructionCovered &&
      jBranchMissed == that.jBranchMissed &&
      jBranchCovered == that.jBranchCovered &&
      jLineMissed == that.jLineMissed &&
      jLineCovered == that.jLineCovered &&
      jComplexityCovered == that.jComplexityCovered &&
      jComplexityMissed == that.jComplexityMissed &&
      jMethodMissed == that.jMethodMissed &&
      jMethodCovered == that.jMethodCovered &&
      Objects.equals(jGroup, that.jGroup) &&
      Objects.equals(jPackage, that.jPackage) &&
      Objects.equals(jClass, that.jClass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jGroup, jPackage, jClass, jInstructionMissed, jInstructionCovered, jBranchMissed, jBranchCovered, jLineMissed, jLineCovered, jComplexityCovered, jComplexityMissed, jMethodMissed, jMethodCovered);
  }
}
