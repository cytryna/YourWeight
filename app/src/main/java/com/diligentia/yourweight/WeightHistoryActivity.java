package com.diligentia.yourweight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.domain.Item;
import com.domain.ItemsAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by rwichrowski on 07.04.16.
 */
public class WeightHistoryActivity extends Activity {

    ArrayList<Item> weightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weightList  = new ArrayList<Item>();
        setContentView(R.layout.activity_weight_history);


        // Construct the data source

        fillTemplateWeight();
// Create the adapter to convert the array to views

        ItemsAdapter adapter = new ItemsAdapter(this, weightList);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter);


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