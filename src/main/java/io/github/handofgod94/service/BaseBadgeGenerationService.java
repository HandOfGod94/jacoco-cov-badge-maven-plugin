package io.github.handofgod94.service;

import io.github.handofgod94.domain.Badge;
import io.github.handofgod94.domain.coverage.Coverage;

class BaseBadgeGenerationService {


  @Deprecated
  protected Badge initializeBadge(Coverage coverage, String badgeLabel) {
    int badgeValue = (int) Math.floor(coverage.getCoveragePercentage());
    Badge badge = Badge.create(badgeLabel, badgeValue);

    return badge;
  }

}
