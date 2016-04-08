package com.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private Date date;
    private BigDecimal weight;
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Item(Date date, BigDecimal weight) {
        this.date = date;
        this.weight = weight;
    }


    public String getDateString() {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getWeight() {
        return weight;
    }
}
