package com.diligentia.yourweight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rwichrowski on 07.04.16.
 */
public class AddWeightActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_weight);

        Button ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                TextView nameTextView = (TextView) findViewById(R.id.name);

                String name = nameTextView.getText().toString();
                String gender = "";

                Toast.makeText(getApplicationContext(), "O " + gender + name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}