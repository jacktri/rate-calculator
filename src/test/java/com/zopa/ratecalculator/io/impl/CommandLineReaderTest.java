package com.zopa.ratecalculator.io.impl;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CommandLineReaderTest {

    private CommandLineReader commandLineReader;

    @Before
    public void setUp() {
        commandLineReader = new CommandLineReader();
    }

    @Test(expected = FileNotFoundException.class)
    public void readNonExistentFile() throws Exception{
        commandLineReader.readLenders("not-here.csv");
    }

    //ToDo fix so it can read from both classpath and normal directories
    @Test
    @Ignore
    public void read() throws Exception{
        commandLineReader.readLenders("classpath:TestData.csv");
    }
}