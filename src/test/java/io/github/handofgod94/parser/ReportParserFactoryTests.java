package io.github.handofgod94.parser;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportParserFactoryTests {

  @Test
  void create_returns_CSVReportParser_for_jacoco_csv_report() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("reprot.csv");
    ReportParser parser = ReportParserFactory.create(file);
    assertTrue(parser instanceof CSVReportParser);
  }

  @Test
  void create_throws_NotImplementedException_for_invalid_jacoco_report_file_ext() {
    File file = mock(File.class);
    when(file.getName()).thenReturn("foo.bar");
    assertThrows(NotImplementedException.class, () -> {
      ReportParserFactory.create(file);
    });
  }
}
