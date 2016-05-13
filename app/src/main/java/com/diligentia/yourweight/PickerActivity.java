package com.diligentia.yourweight;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class PickerActivity extends Activity {

    NumberPicker np1, np2;
    TextView tv1;
    private String value = "0.0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}