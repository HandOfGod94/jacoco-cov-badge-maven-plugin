package io.github.handofgod94;

import java.io.File;
import java.util.Optional;

public class BadgeUtility {

  public static final Optional<String> getFileExt(File file) {
    int i = file.getName().lastIndexOf(".");
    if (i > 0) {
      return Optional.of(file.getName().substring(i + 1));
    }
    return Optional.empty();
  }
}
