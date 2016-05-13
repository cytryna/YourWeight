package com.diligentia.yourweight;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.diligentia.domain.Item;

import java.math.BigDecimal;
import java.util.Date;

public class PickerActivity extends Activity {

    NumberPicker np1, np2;
    TextView tv1;
    private String value = "0.0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Repository repository = Repository.getInstance(this);
        setContentView(R.layout.activity_numberpicker);

        np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        tv1 = (TextView) findViewById(R.id.textView2);

        np1.setMinValue(60);
        np1.setMaxValue(150);
        np2.setMinValue(0);
        np2.setMaxValue(10);
        np1.setWrapSelectorWheel(false);

        np1.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String[] split = value.split("\\.");
                value = String.valueOf(newVal).concat(".").concat(split[1]);
                tv1.setText(value);
            }
        });
        np2.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String[] split = value.split("\\.");
                value = split[0].concat(".").concat(String.valueOf(newVal));
                tv1.setText(value);
            }
        });

        Button ok = (Button) findViewById(R.id.saveButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                repository.addWeight(new Item(new Date(), new BigDecimal(value)));
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}