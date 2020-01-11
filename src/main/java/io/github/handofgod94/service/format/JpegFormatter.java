package io.github.handofgod94.service.format;

import io.vavr.control.Try;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;

/**
 * Converts rendered SVG text to JPG and write it to output.
 */
public class JpegFormatter implements Formatter {

  public static final float JPEG_QUALITY = 1.0f;

  @Override
  public Try<Void> save(File file, String text) {
    JPEGTranscoder transcoder = new JPEGTranscoder();
    transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, JPEG_QUALITY);

    TranscoderInput input = new TranscoderInput(new StringReader(text));

    return Try.withResources(() -> new FileOutputStream(file))
              .of(outputStream -> {
                TranscoderOutput output = new TranscoderOutput(outputStream);
                transcoder.transcode(input, output);
                return null;
              });
  }

}
