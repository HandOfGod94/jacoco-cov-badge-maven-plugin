package io.github.handofgod94.format;

import java.io.*;

import io.vavr.control.Try;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * Converts rendered SVG text to transparent PNG and saves it.
 */
public class PngFormatter implements Formatter {

  @Override
  public Try<Void> save(File file, String text) {
    PNGTranscoder transcoder = new PNGTranscoder();

    TranscoderInput input = new TranscoderInput(new StringReader(text));

    return Try.withResources(() -> new FileOutputStream(file))
              .of(outputStream -> {
                TranscoderOutput output = new TranscoderOutput(outputStream);
                transcoder.transcode(input, output);
                return null;
              });
  }

}
