package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.generator.BadgeProcessState;

public interface Task {
  void perform(BadgeProcessState state);
}
