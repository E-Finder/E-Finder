package com.example.efinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> data;

    public CustomAdapter(Context context, int resource, List<String> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recursos_layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.text);
        String item = getItem(position);
        textView.setText(item);

        return convertView;
    }
}
