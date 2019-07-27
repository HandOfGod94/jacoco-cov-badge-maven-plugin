package io.github.handofgod94.parser;

import io.github.handofgod94.domain.Report;

import java.io.Reader;

public interface ReportParser {
  Report parseReport(Reader reader);
}
