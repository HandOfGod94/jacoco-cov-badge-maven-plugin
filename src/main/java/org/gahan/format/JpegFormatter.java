package org.gahan.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

/**
 * Converts rendered SVG text to JPG and write it to output.
 */
public class JpegFormatter implements Formatter {

  public static final float JPEG_QUALITY = 1.0f;

  @Override
  public void save(File file, String text) throws IOException, TranscoderException {
    JPEGTranscoder transcoder = new JPEGTranscoder();
    transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, JPEG_QUALITY);

    TranscoderInput input = new TranscoderInput(new StringReader(text));
    OutputStream outputStream = new FileOutputStream(file);
    TranscoderOutput output = new TranscoderOutput(outputStream);

    transcoder.transcode(input, output);
    outputStream.flush();
    outputStream.close();
  }

}
