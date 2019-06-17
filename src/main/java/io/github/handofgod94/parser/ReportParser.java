package io.github.handofgod94.parser;

import io.github.handofgod94.domain.ReportLine;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface ReportParser {
  List<ReportLine> parseReport(Reader reader) throws IOException;
}
