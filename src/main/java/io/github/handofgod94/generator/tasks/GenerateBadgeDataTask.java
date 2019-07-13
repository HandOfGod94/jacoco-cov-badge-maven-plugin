package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.Coverage;
import io.github.handofgod94.generator.BadgeProcessState;

public class GenerateBadgeDataTask implements Task {
  @Override
  public void perform(BadgeProcessState state) {

    if (state.getCoverage() == null) throw new IllegalStateException("Invalid coverage provided");
    if (state.getBadgeLabel() == null) throw new IllegalStateException("Invalid badge label provided");

    Coverage coverage = state.getCoverage();
    String badgeLabel = state.getBadgeLabel();

    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = new Badge(badgeLabel, badgeValue);

    state.setBadge(badge);
  }
}
