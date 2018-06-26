package com.zopa.ratecalculator.io.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.io.Reader;

public class CommandLineReader implements Reader {

    private static final String CSV_SPLIT_BY = ",";
    private static final int LENDER_POSITION = 0;
    private static final int RATE_POSITION = 1;
    private static final int AVAILABLE_POSITION = 2;

    @Override
    public List<Lender> readLenders(String file) throws IOException {
        List<Lender> lenders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] inputs = line.split(CSV_SPLIT_BY);
                lenders.add(new Lender(inputs[LENDER_POSITION], Float.parseFloat(inputs[RATE_POSITION]),
                    Integer.parseInt(inputs[AVAILABLE_POSITION])));
            }
        }
        return lenders;
    }
}
