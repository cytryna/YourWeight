package com.diligentia.domain;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private Date date;
    private BigDecimal weight;
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UnitMetric unitMetric;

    public Item(BigDecimal weight, UnitMetric unitMetric) {
        this.date = new Date();
        this.weight = weight;
        this.unitMetric = unitMetric;
    }

    public Item(String s, UnitMetric unitMetric) {
        this.unitMetric = unitMetric;
        String[] split = s.split("_");
        try {
            date = SIMPLE_DATE_FORMAT.parse(split[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        weight = new BigDecimal(split[1]).multiply(unitMetric.getMultiplier());
    }


    public String getDateString() {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getWeight() {
        return weight.divide(unitMetric.getMultiplier());
    }

    public String getSetItem() {
        return  getDateString()+"_"+weight;
    }
}
