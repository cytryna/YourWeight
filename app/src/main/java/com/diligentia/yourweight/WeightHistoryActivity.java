package com.diligentia.yourweight;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by rwichrowski on 07.04.16.
 */
public class WeightHistoryActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_history);


//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_weight_history_item, mobileArray);
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


}