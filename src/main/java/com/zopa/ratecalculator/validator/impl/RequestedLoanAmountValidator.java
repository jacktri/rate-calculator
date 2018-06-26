package com.zopa.ratecalculator.validator.impl;

import com.zopa.ratecalculator.validator.Validator;

public class RequestedLoanAmountValidator implements Validator<Integer> {

    private static final int MINIMUM_VALUE = 1000;
    private static final int MAXIMUM_VALUE = 15000;
    private static final int MULTIPLE = 100;

    @Override
    public boolean valid(Integer input) {
        if(input < MINIMUM_VALUE || input > MAXIMUM_VALUE){
            return false;
        }
        // must be a multiple of £100
        else if(input % MULTIPLE != 0){
            return false;
        }
        return true;
    }
}
