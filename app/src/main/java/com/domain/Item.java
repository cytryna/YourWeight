package com.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private Date date;
    private BigDecimal weight;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Item(Date date, BigDecimal weight) {
        this.date = date;
        this.weight = weight;
    }


    public String getDateString() {
        return simpleDateFormat.format(date);
    }

    public BigDecimal getWeight() {
        return weight;
    }
}
