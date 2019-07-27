package io.github.handofgod94.format;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Factory class to generate formatter based on configuration i.e. (jpg, png, svg, etc).
 */
public class FormatterFactory {

  public static final String JPEG_EXT = "jpg";
  public static final String PNG_EXT = "png";
  public static final String SVG_EXT = "svg";

  /**
   * Factory method which will produce Formatter, which can be used
   * to create files of different formats for the badge.
   * @param ext extension of the file format needed
   * @return Instance of {@link JpegFormatter}, {@link PngFormatter} or {@link SvgFormatter}.
   * @throws NotImplementedException if invalid extension is provided.
   */
  public static Formatter createFormatter(String ext) {

    if (ext.equals(JPEG_EXT)) {
      return new JpegFormatter();
    } else if (ext.equals(PNG_EXT)) {
      return new PngFormatter();
    } else if (ext.equals(SVG_EXT)) {
      return new SvgFormatter();
    } else {
      throw new NotImplementedException(String.format("Format: %s is not yet supported", ext));
    }
  }
}
