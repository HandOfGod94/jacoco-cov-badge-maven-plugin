package io.github.handofgod94.service.parser;

import io.github.handofgod94.domain.Report;

import java.io.File;

public interface ReportParser {
  Report parseReport(File file);
}
