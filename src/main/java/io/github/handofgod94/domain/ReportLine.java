package io.github.handofgod94.domain;


import java.util.Objects;

/**
 * Represents each line in jacoco report
 */
public class ReportLine {

  private final String jGroup;
  private final String jPackage;
  private final String jClass;
  private final int jInstructionMissed;
  private final int jInstructionCovered;
  private final int jBranchMissed;
  private final int jBranchCovered;
  private final int jLineMissed;
  private final int jLineCovered;
  private final int jComplexityCovered;
  private final int jComplexityMissed;
  private final int jMethodMissed;
  private final int jMethodCovered;


  ReportLine(String jGroup,
             String jPackage,
             String jClass,
             int jInstructionMissed, int jInstructionCovered,
             int jBranchMissed, int jBranchCovered,
             int jLineMissed, int jLineCovered,
             int jComplexityMissed, int jComplexityCovered,
             int jMethodMissed, int jMethodCovered) {
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

  public int getjInstructionMissed() {
    return jInstructionMissed;
  }

  public int getjInstructionCovered() {
    return jInstructionCovered;
  }

  public int getjBranchMissed() {
    return jBranchMissed;
  }

  public int getjBranchCovered() {
    return jBranchCovered;
  }

  public int getjLineMissed() {
    return jLineMissed;
  }

  public int getjLineCovered() {
    return jLineCovered;
  }

  public int getjComplexityCovered() {
    return jComplexityCovered;
  }

  public int getjComplexityMissed() {
    return jComplexityMissed;
  }

  public int getjMethodMissed() {
    return jMethodMissed;
  }

  public int getjMethodCovered() {
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
