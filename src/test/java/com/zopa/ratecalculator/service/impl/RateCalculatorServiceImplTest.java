package com.zopa.ratecalculator.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.domain.RepaymentsSummary;
import com.zopa.ratecalculator.exception.InsufficientLendersException;
import com.zopa.ratecalculator.io.Reader;
import com.zopa.ratecalculator.io.Writer;
import com.zopa.ratecalculator.payments.Calculator;
import com.zopa.ratecalculator.validator.Validator;

public class RateCalculatorServiceImplTest {

    private RateCalculatorServiceImpl rateCalculatorService;

    @Mock
    private Calculator calculator;
    @Mock
    private Reader reader;
    @Mock
    private Writer writer;
    @Mock
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        rateCalculatorService = new RateCalculatorServiceImpl(reader, writer, calculator, validator);
    }

    @Test
    public void calculate() throws IOException {
        List<Lender> lenders = lenders();
        RepaymentsSummary repaymentsSummary = repaymentsSummary();
        when(reader.readLenders("file")).thenReturn(lenders);
        when(validator.valid(1500)).thenReturn(true);
        when(calculator.calculate(lenders, 1500)).thenReturn(repaymentsSummary);

        rateCalculatorService.calculate(new String[]{ "file", "1500" });

        verify(writer).write(repaymentsSummary);
    }

    @Test
    public void invalidNumberInput() {
        rateCalculatorService.calculate(new String[]{ "file", "not-a-number" });

        verify(writer).write("second argument must be a valid number");
    }

    @Test
    public void tooManyArguments() {
        rateCalculatorService.calculate(new String[]{ "file", "12", "anotherArg" });

        verify(writer).write("must contain 2 arguments, a csv of lenders and a loan amount");
    }

    @Test
    public void notEnoughArguments() {
        rateCalculatorService.calculate(new String[]{ "file" });

        verify(writer).write("must contain 2 arguments, a csv of lenders and a loan amount");
    }

    @Test
    public void invalidCSV() throws IOException {
        when(reader.readLenders("file")).thenThrow(new IOException());

        rateCalculatorService.calculate(new String[]{ "file", "1500" });

        verify(writer).write("Invalid CSV file");
    }

    @Test
    public void invalidAmount() throws IOException {
        List<Lender> lenders = lenders();
        when(reader.readLenders("file")).thenReturn(lenders);
        when(validator.valid(300)).thenReturn(false);

        rateCalculatorService.calculate(new String[]{ "file", "300" });

        verify(writer).write("Invalid loan request. Must be between £1000 and £15000, and be a multiple of 100");
    }

    @Test
    public void insufficientLenders() throws IOException {
        List<Lender> lenders = lenders();
        when(reader.readLenders("file")).thenReturn(lenders);
        when(validator.valid(1500)).thenReturn(true);
        when(calculator.calculate(lenders, 1500)).thenThrow(new InsufficientLendersException());

        rateCalculatorService.calculate(new String[]{ "file", "1500" });

        verify(writer).write("It is not possible to provide a quote at this time");
    }

    private List<Lender> lenders() {
        return Arrays.asList(new Lender("a", 5, 5)
            , new Lender("b", 5, 5)
            , new Lender("c", 5, 5));
    }

    private RepaymentsSummary repaymentsSummary() {
        return new RepaymentsSummary(1500, 5, 5, 5);
    }
}