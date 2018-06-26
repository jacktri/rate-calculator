package com.zopa.ratecalculator.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.exception.InsufficientLendersException;

public class RateCalculatorUtilTest {

    @Test
    public void sortLendersLowestRateToHighest() {
        List<Lender> lenders = lenders();
        RateCalculatorUtil.sortLendersLowestRateToHighest(lenders);
        assertEquals(lendersInOrder(),lenders);
    }

    @Test
    public void determineRate() {
        List<Lender> lenders = lendersInOrder();
        float result = RateCalculatorUtil.determineAverageRate(lenders,10);
        assertEquals(1.5F,result,0.0002);
    }

    @Test
    public void determineRateOnlyConsumeHalfALender() {
        List<Lender> lenders = Arrays.asList(new Lender("c", 1, 50)
            ,new Lender("c", 2, 100));
        float result = RateCalculatorUtil.determineAverageRate(lenders,100);
        assertEquals(1.5F,result,0.0002);
    }

    @Test(expected = InsufficientLendersException.class)
    public void insufficientLendersWhenDeterminingRate() {
        List<Lender> lenders = Arrays.asList(new Lender("c", 1, 50));
        RateCalculatorUtil.determineAverageRate(lenders,100);
    }

    @Test
    public void findTotalRepayments() {
        long result = RateCalculatorUtil.determineTotalRepayment(2,5);
        assertEquals(10,result);
    }

    @Test
    public void findMonthlyRepaymentsNoInterest() {
        long payment = RateCalculatorUtil.determineMonthlyRepayments(10,10000,0);
        assertEquals(100000, payment);
    }

    @Test
    public void findMonthlyRepaymentsWithInterest() {
        long payment = RateCalculatorUtil.determineMonthlyRepayments(10,10000,0.1F);
        assertEquals(110000, payment);
    }

    @Test
    public void convertIntCurrencyToPrintableFormat() {
        String result = RateCalculatorUtil.convertIntCurrencyToPrintableFormat(2344);
        assertEquals("23.44", result);
    }

    private List<Lender> lenders() {
        return Arrays.asList(new Lender("a", 2, 5)
            , new Lender("b", 3, 5)
            , new Lender("c", 1, 5));
    }

    private List<Lender> lendersInOrder() {
        return Arrays.asList(new Lender("c", 1, 5)
            , new Lender("a", 2, 5)
            , new Lender("b", 3, 5));
    }
}