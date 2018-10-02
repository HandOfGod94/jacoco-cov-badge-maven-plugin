package org.gahan.format;

import java.io.File;
import java.io.IOException;
import org.apache.batik.transcoder.TranscoderException;

/**
 * Interface for converting svg rendered text to output file formats.
 * These includes formatting to 'jpg', 'svg' or 'png'.
 */
public interface Formatter {

  /**
   * Saves the file based on the format selected.
   * @param file File to which the output will be written
   * @param text rendered svg text having all the coverage related info
   * @throws IOException if unable to create file at the specified location
   * @throws TranscoderException if unable to transcode the svg to its format
   */
  public void save(File file, String text) throws IOException, TranscoderException;
}
