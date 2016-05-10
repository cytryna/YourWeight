package com.diligentia.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private Date date;
    private BigDecimal weight;
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Deprecated
    public Item(Date date, BigDecimal weight) {
        this.date = date;
        this.weight = weight;
    }

    public Item(String s) {
        String[] split = s.split("_");
        try {
            date = SIMPLE_DATE_FORMAT.parse(split[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        weight = new BigDecimal(split[1]);
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

    public String getSetItem() {
        return  getDateString()+"_"+weight;
    }
}
