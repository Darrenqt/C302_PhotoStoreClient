package com.example.a16022809.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private ArrayList<Photo> alPhoto;
    private Context context;
    private TextView tvCategory, tvDetails;

    public PhotoAdapter(Context context, int resource, ArrayList<Photo> objects) {
        super(context, resource, objects);
        alPhoto = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.photo_row, parent, false);

        tvCategory = (TextView) rowView.findViewById(R.id.tvCategory);
        tvDetails = (TextView) rowView.findViewById(R.id.tvDetails);

        Photo photo = alPhoto.get(position);
        tvCategory.setText(photo.getCategory_id());
        tvDetails.setText(photo.getDescription() + " " + photo.getCreated_by() + " " + photo.getImage());

        return rowView;
    }

}