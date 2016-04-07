package com.diligentia.yourweight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
