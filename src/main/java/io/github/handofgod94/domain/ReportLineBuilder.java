package io.github.handofgod94.domain;

public class ReportLineBuilder {
  private String groupName = "";
  private String packageName = "";
  private String className = "";
  private int instructionMissed = 0;
  private int instructionCovered = 0;
  private int branchMissed = 0;
  private int branchCovered = 0;
  private int lineMissed = 0;
  private int lineCovered = 0;
  private int complexityCovered = 0;
  private int complexityMissed = 0;
  private int methodMissed = 0;
  private int methodCovered = 0;

  public ReportLineBuilder addGroupName(String group) {
    this.groupName = group;
    return this;
  }

  public ReportLineBuilder addPackageName(String packageName) {
    this.packageName = packageName;
    return this;
  }

  public ReportLineBuilder addClassName(String className) {
    this.className = className;
    return this;
  }

  public ReportLineBuilder addInstructionMissed(int instructionMissed) {
    this.instructionMissed = instructionMissed;
    return this;
  }


  public ReportLineBuilder addInstructionCovered(int instructionCovered) {
    this.instructionCovered = instructionCovered;
    return this;
  }

  public ReportLineBuilder addBranchMissed(int branchMissed) {
    this.branchMissed = branchMissed;
    return this;
  }

  public ReportLineBuilder addBranchCovered(int branchCovered) {
    this.branchCovered = branchCovered;
    return this;
  }

  public ReportLineBuilder addLineMissed(int lineMissed) {
    this.lineMissed = lineMissed;
    return this;
  }

  public ReportLineBuilder addLineCovered(int lineCovered) {
    this.lineCovered = lineCovered;
    return this;
  }

  public ReportLineBuilder addComplexityMissed(int complexityMissed) {
    this.complexityMissed = complexityMissed;
    return this;
  }

  public ReportLineBuilder addComplexityCovered(int complexityCovered) {
    this.complexityCovered = complexityCovered;
    return this;
  }

  public ReportLineBuilder addMethodMissed(int methodMissed) {
    this.methodMissed = methodMissed;
    return this;
  }

  public ReportLineBuilder addMethodCovered(int methodCovered) {
    this.methodCovered = methodCovered;
    return this;
  }

  public ReportLine build() {
    return new ReportLine(groupName, packageName, className,
      instructionMissed, instructionCovered,
      branchMissed, branchCovered,
      lineMissed, lineCovered,
      complexityMissed, complexityCovered,
      methodMissed, methodCovered);
  }
}
