package com.zopa.ratecalculator.service.impl;

import java.io.IOException;
import java.util.List;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.domain.RepaymentsSummary;
import com.zopa.ratecalculator.exception.InsufficientLendersException;
import com.zopa.ratecalculator.io.Reader;
import com.zopa.ratecalculator.io.Writer;
import com.zopa.ratecalculator.payments.Calculator;
import com.zopa.ratecalculator.service.RateCalculatorService;
import com.zopa.ratecalculator.validator.Validator;

public class RateCalculatorServiceImpl implements RateCalculatorService{

    private static final int ARGUMENT_TOTAL = 2;
    private Reader reader;
    private Writer writer;
    private Calculator calculator;
    private Validator<Integer> validator;

    public RateCalculatorServiceImpl(Reader reader, Writer writer, Calculator calculator, Validator<Integer> validator) {
        this.reader = reader;
        this.writer = writer;
        this.calculator = calculator;
        this.validator = validator;
    }

    @Override
    public void calculate(String[] arguments) {
        if(arguments.length != ARGUMENT_TOTAL) {
            writer.write("must contain 2 arguments, a csv of lenders and a loan amount");
            return;
        }
        int requestedAmount;
        List<Lender> lenders;
        try {
            requestedAmount = Integer.parseInt(arguments[1]);
        }
        catch(NumberFormatException e) {
            writer.write("second argument must be a valid number");
            return;
        }
        try {
            lenders = reader.readLenders(arguments[0]);
        }
        catch(IOException e){
            writer.write("Invalid CSV file");
            return;
        }
        if(!validator.valid(requestedAmount)){
            writer.write("Invalid loan request. Must be between £1000 and £15000, and be a multiple of 100");
            return;
        }
        try {
            RepaymentsSummary summary = calculator.calculate(lenders, requestedAmount);
            writer.write(summary);
        }
        catch(InsufficientLendersException e){
            writer.write("It is not possible to provide a quote at this time");
        }
    }
}
