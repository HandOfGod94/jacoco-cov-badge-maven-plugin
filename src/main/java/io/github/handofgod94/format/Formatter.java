package io.github.handofgod94.format;

import io.vavr.control.Try;

import java.io.File;

/**
 * Interface for converting svg rendered text to output file formats.
 * These includes formatting to 'jpg', 'svg' or 'png'.
 */
public interface Formatter {

  /**
   * Saves the file based on the format selected.
   * @param file File to which the output will be written
   * @param text rendered svg text having all the coverage related info
   */
  Try<Void> save(File file, String text);
}
