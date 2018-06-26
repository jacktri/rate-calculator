package com.zopa.ratecalculator.validator.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zopa.ratecalculator.validator.Validator;

public class RequestedLoanAmountValidatorTest {

    private Validator<Integer> requestedLoanAmountValidator;

    @Before
    public void setUp() throws Exception {
        requestedLoanAmountValidator = new RequestedLoanAmountValidator();
    }

    @Test
    public void tooLowInvalid() {
        boolean result = requestedLoanAmountValidator.valid(500);
        assertFalse(result);
    }

    @Test
    public void tooHighInvalid() {
        boolean result = requestedLoanAmountValidator.valid(15100);
        assertFalse(result);
    }

    @Test
    public void justLowEnoughValid() {
        boolean result = requestedLoanAmountValidator.valid(15000);
        assertTrue(result);
    }

    @Test
    public void justHighEnoughValid() {
        boolean result = requestedLoanAmountValidator.valid(1000);
        assertTrue(result);
    }

    @Test
    public void notAMultipleOf100Invalid() {
        boolean result = requestedLoanAmountValidator.valid(1301);
        assertFalse(result);
    }
}