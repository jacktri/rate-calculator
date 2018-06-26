package com.zopa.ratecalculator.io;

import com.zopa.ratecalculator.domain.RepaymentsSummary;

public interface Writer {
    void write(String output);
    void write(RepaymentsSummary repaymentsSummary);
}
