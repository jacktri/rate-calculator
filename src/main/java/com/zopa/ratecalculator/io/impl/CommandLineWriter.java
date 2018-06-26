package com.zopa.ratecalculator.io.impl;

import static com.zopa.ratecalculator.util.RateCalculatorUtil.convertIntCurrencyToPrintableFormat;

import com.zopa.ratecalculator.domain.RepaymentsSummary;
import com.zopa.ratecalculator.io.Writer;

public class CommandLineWriter implements Writer {

    @Override
    public void write(String output) {
        System.out.println(output);
    }

    @Override
    public void write(RepaymentsSummary repaymentsSummary) {
        write("Requested amount: £" + repaymentsSummary.getRequestedAmount());
        write(String.format("Rate: %.1f", repaymentsSummary.getRate()) + "%");
        write("Monthly repayment: £"
            + convertIntCurrencyToPrintableFormat(repaymentsSummary.getMonthlyRepayment()));
        write("Total repayment: £"
            + convertIntCurrencyToPrintableFormat(repaymentsSummary.getTotalRepayment()));
    }
}
