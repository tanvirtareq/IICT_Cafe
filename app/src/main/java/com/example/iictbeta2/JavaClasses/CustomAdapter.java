package com.example.iictbeta2.JavaClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iictbeta2.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<FoodItem> foodItems;

    public CustomAdapter(Context c, ArrayList<FoodItem> foodItems) {
        this.c = c;
        this.foodItems = foodItems;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView avStat = convertView.findViewById(R.id.availability);

        final FoodItem foodItem = (FoodItem) this.getItem(position);

        itemName.setText(foodItem.getItemName());
        avStat.setText(foodItem.getAvailability());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, foodItem.getItemName(), Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
