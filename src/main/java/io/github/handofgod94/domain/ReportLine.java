package io.github.handofgod94.domain;

import com.google.auto.value.AutoValue;

/**
 * Domain representing a line in jacoco report
 * Different jacoco report formats will have different ways of representing a report.
 * For e.g. CSV Report, each entity is separated by commas,
 * while in Lcov report it will be different.
 * All of them represents same thing.
 */
@AutoValue
public abstract class ReportLine {

  public abstract String getGroupName();
  public abstract String getPackageName();
  public abstract String getClassName();
  public abstract long getInstructionMissed();
  public abstract long getInstructionCovered();
  public abstract long getBranchMissed();
  public abstract long getBranchCovered();
  public abstract long getLineMissed();
  public abstract long getLineCovered();
  public abstract long getComplexityCovered();
  public abstract long getComplexityMissed();
  public abstract long getMethodMissed();
  public abstract long getMethodCovered();

  public static Builder builder() {
    return new AutoValue_ReportLine.Builder()
      .setBranchCovered(0).setBranchMissed(0)
      .setComplexityCovered(0).setComplexityMissed(0)
      .setMethodMissed(0).setMethodCovered(0)
      .setLineCovered(0).setLineMissed(0)
      .setInstructionCovered(0).setInstructionMissed(0);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setGroupName(String groupName);
    public abstract Builder setPackageName(String packageName);
    public abstract Builder setClassName(String className);
    public abstract Builder setInstructionMissed(long instructionMissed);
    public abstract Builder setInstructionCovered(long instructionCovered);
    public abstract Builder setBranchMissed(long branchMissed);
    public abstract Builder setBranchCovered(long branchCovered);
    public abstract Builder setLineMissed(long lineMissed);
    public abstract Builder setLineCovered(long lineCovered);
    public abstract Builder setComplexityMissed(long complexityMissed);
    public abstract Builder setComplexityCovered(long complexityCovered);
    public abstract Builder setMethodMissed(long methodMissed);
    public abstract Builder setMethodCovered(long methodCovered);
    public abstract ReportLine build();
  }
}
