package com.zopa.ratecalculator.io.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zopa.ratecalculator.domain.RepaymentsSummary;
import com.zopa.ratecalculator.io.Writer;

public class CommandLineWriterTest {

    private Writer commandLineWriter;

    @Before
    public void setUp() {
        commandLineWriter = new CommandLineWriter();
    }

    @Test
    public void write() {
    }

    @Test
    public void writeSummary() {
        commandLineWriter.write(new RepaymentsSummary(1000,
            0.1F,5000,5000));
    }
}