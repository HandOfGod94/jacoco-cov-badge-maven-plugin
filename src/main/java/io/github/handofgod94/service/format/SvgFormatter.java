package io.github.handofgod94.service.format;

import io.vavr.control.Try;

import java.io.File;
import java.io.FileWriter;

/**
 * Generates ".svg" badge from the rendered text from the template.
 * It just writes the text provided to "save" method to a file.
 */
public class SvgFormatter implements Formatter {

  @Override
  public Try<Void> save(File file, String text) {
    // Since text is already in svg format, we can directly save it

    return Try.withResources(() -> new FileWriter(file))
      .of(writer ->  {
        writer.write(text);
        return null;
      });
  }

}
