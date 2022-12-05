package com.example.betastudio.general;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySecondAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> sortedList = new ArrayList<>();

    public MySecondAdapter(Context context, ArrayList<String> list) {
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
        TextView tv = (TextView) listItem.findViewById(android.R.id.text1);
        tv.setText(currentString);
        tv.setTextColor(Color.BLACK);
        listItem.setBackgroundColor(Color.parseColor("#B3F4B7FF"));

        return listItem;
    }

}
