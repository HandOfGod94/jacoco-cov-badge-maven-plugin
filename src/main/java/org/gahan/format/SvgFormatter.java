package org.gahan.format;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Generates ".svg" badge from the rendered text from the template.
 * It just writes the text provided to "save" method to a file.
 */
public class SvgFormatter implements Formatter {

  @Override
  public void save(File file, String text) throws IOException {
    // Since text is already in svg format, we can directly save it
    Writer writer = new FileWriter(file);
    writer.write(text);
    writer.flush();
    writer.close();
  }

}
