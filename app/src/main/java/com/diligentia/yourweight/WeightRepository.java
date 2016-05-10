package com.diligentia.yourweight;

import android.content.Context;
import android.content.SharedPreferences;

import com.diligentia.domain.Item;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by radek on 09.04.2016.
 */
public class WeightRepository {

    LinkedList<Item> weightList;

    private static WeightRepository instance;

    private WeightRepository() {
        weightList = new LinkedList<>();
        fillTemplateWeight();
    }

    public static WeightRepository getInstance() {
        if (instance == null) {
            instance = new WeightRepository();
        }
        return instance;
    }

    public LinkedList<Item> getWeightList() {
        return weightList;
    }


    private void fillTemplateWeight() {
        weightList  = new LinkedList<Item>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -101);

        BigDecimal min = new BigDecimal(80);
        BigDecimal max = new BigDecimal(130);
        BigDecimal randomBigDecimal;

        Random r = new Random();
        double randomValue;
        for (int i = 0; i < 100; i++) {
            cal.add(Calendar.DATE, 1);
            randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
            weightList.add(new Item(cal.getTime(), randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP)));
        }
    }
}
