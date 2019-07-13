package io.github.handofgod94.generator;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.generator.tasks.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BadgeGenerator {

  public static final String DEFAULT_BADGE_LABEL = "coverage";

  private final Badge.CoverageCategory category;
  private final String badgeLabel;
  private final File jacocoReportFile;
  private final File outputFile;

  public BadgeGenerator(Badge.CoverageCategory category, String badgeLabel, File jacocoReportFile, File outputFile) {
    this.category = category;
    this.badgeLabel = badgeLabel !=  null ? badgeLabel : DEFAULT_BADGE_LABEL;
    this.jacocoReportFile = jacocoReportFile;
    this.outputFile = outputFile;
  }

  public BadgeProcessState execute() {
    BadgeProcessState state = new BadgeProcessState(jacocoReportFile, outputFile, category, badgeLabel);
    List<Task> taskList = initializeTaskList();

    taskList.forEach(task -> task.perform(state));
    return state;
  }

  private List<Task> initializeTaskList() {
    List<Task> taskList = new ArrayList<>();
    taskList.add(new LoadConfigurationTask());
    taskList.add(new CalculateCoverageTask());
    taskList.add(new GenerateBadgeDataTask());
    taskList.add(new RenderBadgeStringTask());
    taskList.add(new SaveBadgeTask());

    return taskList;
  }
}
