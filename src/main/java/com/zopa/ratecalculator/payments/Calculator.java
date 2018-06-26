package com.zopa.ratecalculator.payments;

import java.util.List;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.domain.RepaymentsSummary;

@FunctionalInterface
public interface Calculator {
    RepaymentsSummary calculate(List<Lender> lenders, int amount);
}
