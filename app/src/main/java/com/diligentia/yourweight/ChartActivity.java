package com.diligentia.yourweight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diligentia.domain.Item;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by radek on 08.04.2016.
 */
public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinkedList<Item> weightList = WeightRepository.getInstance().getWeightList();

        setContentView(R.layout.activity_drawing_chart);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);


        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int i = 0;
        for (int i1 = weightList.size() - 1; i1 >= 0; i1--) {
            Item item = weightList.get(i1);
            System.err.println(item.getWeight());
            entries.add(new Entry(item.getWeight().floatValue(), ++i));
            labels.add(item.getDateString());
        }

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

    }
}
