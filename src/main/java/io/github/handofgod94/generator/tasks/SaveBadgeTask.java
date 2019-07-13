package io.github.handofgod94.generator.tasks;

import io.github.handofgod94.BadgeUtility;
import io.github.handofgod94.format.Formatter;
import io.github.handofgod94.format.FormatterFactory;
import io.github.handofgod94.generator.BadgeProcessState;
import org.apache.batik.transcoder.TranscoderException;

import java.io.File;
import java.io.IOException;

public class SaveBadgeTask implements Task {
  @Override
  public void perform(BadgeProcessState state) {

    if (state.getOutputFile() == null) throw new IllegalStateException("Invalid output file provided");
    if (state.getRenderedString() == null) throw new IllegalStateException("Invalid rendered string provided");
    if (state.getRenderedString() != null && state.getRenderedString().trim().equals("")) throw new IllegalStateException("Blank rendered string");


    try {
      File outputFile = state.getOutputFile();
      String renderedString = state.getRenderedString();

      String fileExt = BadgeUtility.getFileExt(outputFile).orElseThrow(()-> new IllegalArgumentException("Invalid output file provided"));
      Formatter formatter = FormatterFactory.createFormatter(fileExt);
      formatter.save(outputFile, renderedString);
    } catch (IOException | TranscoderException e) {
      e.printStackTrace();
      // TODO: evalute use of vavr
    }

  }
}
