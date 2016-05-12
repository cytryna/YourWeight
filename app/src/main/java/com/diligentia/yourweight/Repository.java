package com.diligentia.yourweight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.diligentia.domain.Item;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Repository {

    private static Repository instance;
    public static final String DATABASE = "database" ;
    public static final String WEIGHT_DATA = "weightdata";
    private SharedPreferences sharedpreferences;
    LinkedList<Item> weightList = new LinkedList<>();

    private Repository(Activity activity) {
        this.sharedpreferences = activity.getSharedPreferences(DATABASE, Context.MODE_PRIVATE);
    }

    public static Repository getInstance(Activity activity) {
        if (instance == null) {
            instance = new Repository(activity);
        }
        return instance;
    }

//    private void fillTemplateWeight() {
//        weightList  = new LinkedList<Item>();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DATE, -101);
//
//        BigDecimal min = new BigDecimal(80);
//        BigDecimal max = new BigDecimal(130);
//        BigDecimal randomBigDecimal;
//
//        Random r = new Random();
//        double randomValue;
//        for (int i = 0; i < 100; i++) {
//            cal.add(Calendar.DATE, 1);
//            randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
//            weightList.add(new Item(cal.getTime(), randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP)));
//        }
//    }

    public LinkedList<Item> getWeightList() {
        weightList = new LinkedList<>();
        Set<String> data = sharedpreferences.getStringSet(WEIGHT_DATA, new HashSet<String>());
        for (String s : data) {
            weightList.add(new Item(s));
        }
//        Collections.reverse(weightList);
        Log.i("sharedpreferences", "1.set = "+sharedpreferences.getStringSet(WEIGHT_DATA, new HashSet<String>()));
        return weightList;
    }

    public void addWeight(Item item1) {
        weightList.add(item1);
        Set<String> strings = new HashSet<String>();
        for (Item item : weightList) {
            strings.add(item.getSetItem());
        }
        sharedpreferences.edit().clear();
        sharedpreferences.edit().putStringSet(WEIGHT_DATA, strings).commit();

    }
}
