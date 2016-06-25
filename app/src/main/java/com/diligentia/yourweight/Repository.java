package com.diligentia.yourweight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.diligentia.domain.Item;
import com.diligentia.domain.UnitMetric;
import com.diligentia.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Repository {

    private static Repository instance;
    public static final String DATABASE = "database" ;
    private static final String LAST_USER = "lastUser";
    private static final String WEIGHT_DATA = "weightdata";
    private static final String USER_DATA = "userdata";
    private SharedPreferences sharedpreferences;
    List<Item> weightList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private UnitMetric unitMetric;
    private boolean autologin;


    private Repository(Activity activity) {
        this.sharedpreferences = activity.getSharedPreferences(DATABASE, Context.MODE_PRIVATE);
    }

    public static Repository getInstance(Activity activity) {
        if (instance == null) {
            instance = new Repository(activity);
        }
        return instance;
    }

    public List<Item> getWeightList() {
        Set<String> data = sharedpreferences.getStringSet(getUserWeightKey(), new HashSet<String>());
        List<String> strings = asSortedList(data);

        weightList = new ArrayList<Item>();
        for (String s : strings) {
            weightList.add(new Item(s, unitMetric));
        }
//
//        Log.i("sharedpreferences", "1.set = "+sharedpreferences.getStringSet(WEIGHT_DATA, new HashSet<String>()));
        return weightList;
    }

    private static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list, Collections.<T>reverseOrder());
        return list;
    }

    public void addWeight(Item item1) {
        weightList.add(item1);
        Set<String> strings = new HashSet<String>();
        for (Item item : weightList) {
            strings.add(item.getStringItem());
        }
        sharedpreferences.edit().clear();
        sharedpreferences.edit().putStringSet(getUserWeightKey(), strings).commit();

    }

    public void deleteWeight() {
        sharedpreferences.edit().clear();
        sharedpreferences.edit().putStringSet(getUserWeightKey(), null).commit();

    }

    private String getUserWeightKey() {
        return WEIGHT_DATA+getLastLoginUser().toLowerCase();
    }


    public List<User> getUserList() {
        Set<String> data = sharedpreferences.getStringSet(USER_DATA, new HashSet<String>());

        userList = new ArrayList<User>();
        for (String s : data) {
            String[] split = s.split("_");
            userList.add(new User(split[0], split[1]));
        }

        return userList;
    }

    public void addUser(User item1) {
        userList.add(item1);
        Set<String> strings = new HashSet<String>();
        for (User item : userList) {
            strings.add(item.getUserString());
        }
        sharedpreferences.edit().clear();
        sharedpreferences.edit().putStringSet(USER_DATA, strings).commit();

    }



    public void setLastLoginUser(String loginUser) {

        sharedpreferences.edit().clear();
        sharedpreferences.edit().putString(LAST_USER, loginUser).commit();
    }

    public String getLastLoginUser() {
        String user = sharedpreferences.getString(LAST_USER, new String());
        return user;
    }

    public UnitMetric getUnitMetric() {
        return unitMetric;
    }

    public void setUnitMetric(String unitMetric) {
        if (UnitMetric.IMPERIAL.toString().equals(unitMetric)) {
            this.unitMetric = UnitMetric.IMPERIAL;
        } else {
            this.unitMetric = UnitMetric.METRIC;
        }
    }

    public void setAutologin(boolean autologin) {
        this.autologin = autologin;
    }

    public boolean getAutologin() {
        return autologin;
    }
}
