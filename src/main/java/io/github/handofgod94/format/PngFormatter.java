package io.github.handofgod94.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * Converts rendered SVG text to transparent PNG and saves it.
 */
public class PngFormatter implements Formatter {

  @Override
  public void save(File file, String text) throws IOException, TranscoderException {
    PNGTranscoder transcoder = new PNGTranscoder();

    TranscoderInput input = new TranscoderInput(new StringReader(text));
    OutputStream outputStream = new FileOutputStream(file);
    TranscoderOutput output = new TranscoderOutput(outputStream);

    transcoder.transcode(input, output);
    outputStream.flush();
    outputStream.close();
  }

}
