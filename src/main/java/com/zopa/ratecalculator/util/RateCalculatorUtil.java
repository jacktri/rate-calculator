package com.zopa.ratecalculator.util;

import static java.util.Collections.sort;
import static java.util.Comparator.comparing;

import java.util.List;

import com.zopa.ratecalculator.domain.Lender;
import com.zopa.ratecalculator.exception.InsufficientLendersException;

public class RateCalculatorUtil {

    private RateCalculatorUtil() {

    }

    public static void sortLendersLowestRateToHighest(List<Lender> lenders) {
        sort(lenders, comparing(Lender::getRate));
    }

    public static float determineAverageRate(List<Lender> lenders, int amount) {
        int total = 0;
        float unDividedTotal = 0;
        for(Lender lender: lenders) {
            int tempTotal = total + lender.getAvailable();
            // total amount required to be lent reached? Finish
            if(tempTotal == amount) {
                total = tempTotal;
                unDividedTotal = unDividedTotal + (lender.getRate() * lender.getAvailable());
                break;
            }
            // have more than the total amount required to be lent? Only take what is needed then finish
            else if(tempTotal > amount) {
                total = tempTotal - (tempTotal - amount);
                unDividedTotal = unDividedTotal + (lender.getRate() * (tempTotal - lender.getAvailable()));
                break;
            }
            // total amount required to be lent not reached continue
            else{
                total = tempTotal;
                unDividedTotal = unDividedTotal + (lender.getRate() * lender.getAvailable());
            }
        }

        // not enough lenders were found return exception
        if(total < amount){
            throw new InsufficientLendersException();
        }

        // determine the average lending rate
        return unDividedTotal / total;
    }

    // ToDO add compound interest algorithm
    public static long determineMonthlyRepayments(int months, int amount, float rate) {
        long preRateAmount = (amount*100) / months;
        return Math.round(preRateAmount * (1+rate));
    }

    public static long determineTotalRepayment(int months, long monthlyRepayment) {
        return months * monthlyRepayment;
    }

    public static String convertIntCurrencyToPrintableFormat(long money) {
        StringBuilder sb = new StringBuilder();
        money = cycleMoney(money /100, money,sb);
        sb.append(".");
        cycleMoney(0, money,sb);
        return sb.reverse().toString();
    }

    private static long cycleMoney(long target, long money, StringBuilder sb) {
        while (money > target) {
            sb.append(money % 10);
            money = money / 10;
        }
        return money;
    }
}
