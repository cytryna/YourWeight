package com.diligentia.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diligentia.yourweight.R;

import java.util.List;

public class ItemsAdapter extends ArrayAdapter<Item> {

    public ItemsAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_weight_history_item, parent, false);
        }
        // Lookup view for data population
        TextView txDate = (TextView) convertView.findViewById(R.id.txDate);
        TextView txWeight = (TextView) convertView.findViewById(R.id.txWeight);
        // Populate the data into the template view using the data object
        txDate.setText(item.getDateString().toString());
        txWeight.setText(item.getWeight().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}