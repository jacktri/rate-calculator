package com.zopa.ratecalculator.io;

import java.io.IOException;
import java.util.List;

import com.zopa.ratecalculator.domain.Lender;

@FunctionalInterface
public interface Reader {
    List<Lender> readLenders(String file) throws IOException;
}
