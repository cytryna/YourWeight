package com.diligentia.yourweight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by rwichrowski on 07.04.16.
 */
public class WeightHistoryActivity extends Activity {

    Map<Date, Double> weightMap;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weightMap = new HashMap<>();
        fillTemplateWeight();
        setContentView(R.layout.activity_weight_history);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


//        Button ok = (Button) findViewById(R.id.ok);
//
//        ok.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                TextView nameTextView = (TextView) findViewById(R.id.name);
//
//                String name = nameTextView.getText().toString();
//                String gender = "";
//
//                Toast.makeText(getApplicationContext(), "O " + gender + name, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void fillTemplateWeight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -100);

        Random r = new Random();
        double randomValue;
        for (int i = 0; i < 100; i++) {
            cal.add(Calendar.DATE, 1);
            randomValue = 100 + (130 - 100) * r.nextDouble();
            weightMap.put(cal.getTime(), randomValue);
            System.err.println(cal.getTime() + " " + randomValue);
        }
    }

//    public static void main(String[] args) {
//        WeightHistoryActivity weightHistoryActivity = new WeightHistoryActivity();
//        weightHistoryActivity.fillTemplateWeight();
//    }
}