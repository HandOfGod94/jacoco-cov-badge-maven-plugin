package io.github.handofgod94.format;

import org.apache.commons.lang3.NotImplementedException;

/**
 * FormatterFactory
 */
public class FormatterFactory {

  public static final String JPEG_EXT = "jpg";
  public static final String PNG_EXT = "png";

  public static Formatter createFormatter(String fileExtension) {

    if (fileExtension.equals(JPEG_EXT)) {
      return new JpegFormatter();
    } else if (fileExtension.equals(PNG_EXT)) {
      return new PngFormatter();
    } else {
      throw new NotImplementedException("Other formats are not supported yet");
    }
  }
}
