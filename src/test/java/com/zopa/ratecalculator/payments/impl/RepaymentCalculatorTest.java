package com.zopa.ratecalculator.payments.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.domain.RepaymentsSummary;

public class RepaymentCalculatorTest {

    private RepaymentCalculator repaymentCalculator;

    @Before
    public void setUp() throws Exception {
        repaymentCalculator = new RepaymentCalculator();
    }

    //ToDO investigate this test
    @Test
    public void calculate() {
        RepaymentsSummary result = repaymentCalculator.calculate(lenders(), 150);
        Assert.assertEquals(
            new RepaymentsSummary(150,50,21216,763776),result);
    }

    private List<Lender> lenders() {
        return Arrays.asList(new Lender("a",99,50)
            ,new Lender("b",50,50)
            ,new Lender("c",1,50));
    }
}