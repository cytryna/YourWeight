package com.diligentia.yourweight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diligentia.domain.Item;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Repository repository = Repository.getInstance(this);

        List<Item> weightList = repository.getWeightList();

        setContentView(R.layout.activity_drawing_chart);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);


        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int i = 0;
        for (int i1 = weightList.size() - 1; i1 >= 0; i1--) {
            Item item = weightList.get(i1);
//            Log.i("radek", ""+item.getWeight().floatValue());
            entries.add(new Entry(item.getWeight().floatValue(), i++));
            labels.add(item.getDateString());
        }

        LineDataSet dataset = new LineDataSet(entries, "");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.PASTEL_COLORS);
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

    }
}
