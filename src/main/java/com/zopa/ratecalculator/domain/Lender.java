package com.zopa.ratecalculator.domain;

import java.util.Objects;

public class Lender {
    private String name;
    private float rate;
    private int available;

    public Lender(String name, float rate, int available) {
        this.name = name;
        this.rate = rate;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lender)) {
            return false;
        }
        Lender lender = (Lender) o;
        return Float.compare(lender.rate, rate) == 0 &&
            available == lender.available &&
            Objects.equals(name, lender.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rate, available);
    }
}
