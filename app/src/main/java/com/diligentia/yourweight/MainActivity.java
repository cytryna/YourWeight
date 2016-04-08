package com.diligentia.yourweight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.domain.Item;
import com.domain.ItemsAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> weightList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillTemplateWeight();
        setContentView(R.layout.activity_main);
        Button ok = (Button) findViewById(R.id.add_weight_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.weight);
                String text = editText.getText().toString();

                Double weightToday = Double.parseDouble(text.isEmpty() ? "120.5" : text);
//                Toast.makeText(getApplicationContext(), "editText.getText() " + weightToday, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), WeightHistoryActivity.class);
                startActivity(intent);
            }
        });

        // Construct the data source


// Create the adapter to convert the array to views

        Collections.reverse(weightList);
        ItemsAdapter adapter = new ItemsAdapter(this, weightList);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Wybrano element " + position + ", czyli " + weightList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);

    }

    public void fillTemplateWeight() {
        weightList  = new ArrayList<Item>();
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
