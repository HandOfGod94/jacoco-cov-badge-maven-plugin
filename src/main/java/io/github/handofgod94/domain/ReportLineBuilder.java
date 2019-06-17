package io.github.handofgod94.domain;

public class ReportLineBuilder {
  private String jGroup = "";
  private String jPackage = "";
  private String jClass = "";
  private int jInstructionMissed = 0;
  private int jInstructionCovered = 0;
  private int jBranchMissed = 0;
  private int jBranchCovered = 0;
  private int jLineMissed = 0;
  private int jLineCovered= 0 ;
  private int jComplexityCovered = 0;
  private int jComplexityMissed = 0;
  private int jMethodMissed = 0;
  private int jMethodCovered = 0;

  public ReportLineBuilder addGroup(String jGroup) {
    this.jGroup = jGroup;
    return this;
  }

  public ReportLineBuilder addPackage(String jPackage) {
    this.jPackage = jPackage;
    return this;
  }

  public ReportLineBuilder addClass(String jClass) {
    this.jClass = jClass;
    return this;
  }

  public ReportLineBuilder addJInstructionMissed(int jInstructionMissed) {
    this.jInstructionMissed = jInstructionMissed;
    return this;
  }


  public ReportLineBuilder addJInstructionCovered(int jInstructionCovered) {
    this.jInstructionCovered = jInstructionCovered;
    return this;
  }
  public ReportLineBuilder addJBranchMissed(int jBranchMissed) {
    this.jBranchMissed = jBranchMissed;
    return this;
  }
  public ReportLineBuilder addJBranchCovered(int jBranchCovered) {
    this.jBranchCovered = jBranchCovered;
    return this;
  }
  public ReportLineBuilder addJLineMissed(int jLineMissed) {
    this.jLineMissed = jLineMissed;
    return this;
  }
  public ReportLineBuilder addJLineCovered(int jLineCovered) {
    this.jLineCovered = jLineCovered;
    return this;
  }
  public ReportLineBuilder addJComplexityMissed(int jComplexityMissed) {
    this.jComplexityMissed = jComplexityMissed;
    return this;
  }
  public ReportLineBuilder addJComplexityCovered(int jComplexityCovered) {
    this.jComplexityCovered = jComplexityCovered;
    return this;
  }
  public ReportLineBuilder addJMethodMissed(int jMethodMissed) {
    this.jMethodMissed = jMethodMissed;
    return this;
  }
  public ReportLineBuilder addJMethodCovered(int jMethodCovered) {
    this.jMethodCovered = jMethodCovered;
    return this;
  }

  public ReportLine build() {
    return new ReportLine(jGroup, jPackage, jClass,
                          jInstructionMissed, jInstructionCovered,
                          jBranchMissed, jBranchCovered,
                          jLineMissed, jLineCovered,
                          jComplexityMissed, jComplexityCovered,
                          jMethodMissed, jMethodCovered);
  }
}
