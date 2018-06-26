package com.zopa.ratecalculator;

import org.junit.Test;

public class RateCalculatorApplicationTest {

    @Test
    public void fullTest() {
        RateCalculatorApplication.main(new String[]{
            "C:\\Users\\earwi\\Desktop\\sky\\rate-calculator2\\src\\main\\resources\\Market Data for Exercise.csv",
            "1500"});
    }

}