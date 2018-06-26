package com.zopa.ratecalculator;

import com.zopa.ratecalculator.io.Reader;
import com.zopa.ratecalculator.io.Writer;
import com.zopa.ratecalculator.io.impl.CommandLineReader;
import com.zopa.ratecalculator.io.impl.CommandLineWriter;
import com.zopa.ratecalculator.payments.Calculator;
import com.zopa.ratecalculator.payments.impl.RepaymentCalculator;
import com.zopa.ratecalculator.service.RateCalculatorService;
import com.zopa.ratecalculator.service.impl.RateCalculatorServiceImpl;
import com.zopa.ratecalculator.validator.Validator;
import com.zopa.ratecalculator.validator.impl.RequestedLoanAmountValidator;

public class RateCalculatorApplication {

    public static void main(String[] args) {
        Reader reader = new CommandLineReader();
        Writer writer = new CommandLineWriter();
        Calculator calculator = new RepaymentCalculator();
        Validator<Integer> validator = new RequestedLoanAmountValidator();
        RateCalculatorService rateCalculatorService = new RateCalculatorServiceImpl(reader,writer,calculator,validator);
        rateCalculatorService.calculate(args);
    }
}
