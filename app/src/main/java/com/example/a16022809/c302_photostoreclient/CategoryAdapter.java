package com.example.a16022809.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private ArrayList<Category> alCategory;
    private Context context;
    private TextView tvName, tvDesc;

    public CategoryAdapter(Context context, int resource, ArrayList<Category> objects) {
        super(context, resource, objects);
        alCategory = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.category_row, parent, false);

        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);

        Category category = alCategory.get(position);
        tvName.setText(category.getName());
        tvDesc.setText(category.getDescription());

        return rowView;
    }

}
