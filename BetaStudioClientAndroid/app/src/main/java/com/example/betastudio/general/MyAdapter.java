package com.example.betastudio.general;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.betastudio.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> sortedList = new ArrayList<>();

    public MyAdapter(Context context, ArrayList<String> list) {
        super(context, 0 , list);
        mContext = context;
        sortedList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,parent,false);

        String currentString = sortedList.get(position);
        String[] parts = currentString.split("#");

        TextView tv = (TextView) listItem.findViewById(android.R.id.text1);
        tv.setText(parts[0]+"\n");
        tv.setTextColor(Color.BLACK);

        Double priority = Double.valueOf(parts[1]);
        listItem.setBackgroundColor(ColorBasedPriority.get(priority));

        return listItem;
    }

}
