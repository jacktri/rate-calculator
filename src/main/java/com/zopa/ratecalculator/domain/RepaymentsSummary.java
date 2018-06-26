package com.zopa.ratecalculator.domain;

import java.util.Objects;

public class RepaymentsSummary {
    private int requestedAmount;
    private float rate;
    private long monthlyRepayment;
    private long totalRepayment;

    public RepaymentsSummary(int requestedAmount, float rate, long monthlyRepayment, long totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public float getRate() {
        return rate;
    }

    public long getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public long getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepaymentsSummary)) {
            return false;
        }
        RepaymentsSummary that = (RepaymentsSummary) o;
        return requestedAmount == that.requestedAmount &&
            Double.compare(that.rate, rate) == 0 &&
            Double.compare(that.monthlyRepayment, monthlyRepayment) == 0 &&
            Double.compare(that.totalRepayment, totalRepayment) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestedAmount, rate, monthlyRepayment, totalRepayment);
    }
}
