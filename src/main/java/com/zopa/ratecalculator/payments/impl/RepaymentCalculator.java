package com.zopa.ratecalculator.payments.impl;

import static com.zopa.ratecalculator.util.RateCalculatorUtil.determineAverageRate;
import static com.zopa.ratecalculator.util.RateCalculatorUtil.determineMonthlyRepayments;
import static com.zopa.ratecalculator.util.RateCalculatorUtil.determineTotalRepayment;
import static com.zopa.ratecalculator.util.RateCalculatorUtil.sortLendersLowestRateToHighest;

import java.util.List;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.domain.RepaymentsSummary;
import com.zopa.ratecalculator.payments.Calculator;

public class RepaymentCalculator implements Calculator{

    private static final int NUMBER_OF_MONTHS = 36;

    @Override
    public RepaymentsSummary calculate(List<Lender> lenders, int amount) {
        sortLendersLowestRateToHighest(lenders);
        float rate = determineAverageRate(lenders, amount);
        long monthlyPayments = determineMonthlyRepayments(NUMBER_OF_MONTHS,amount, rate);
        long totalPayment = determineTotalRepayment(NUMBER_OF_MONTHS, monthlyPayments);
        return new RepaymentsSummary(amount,rate,monthlyPayments,totalPayment);
    }

}
