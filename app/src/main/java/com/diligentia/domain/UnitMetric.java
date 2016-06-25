package com.diligentia.domain;

import java.math.BigDecimal;

public enum UnitMetric {
    METRIC(BigDecimal.ONE),
    IMPERIAL(new BigDecimal(2.2046));

    private BigDecimal multiplier;

    UnitMetric(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
