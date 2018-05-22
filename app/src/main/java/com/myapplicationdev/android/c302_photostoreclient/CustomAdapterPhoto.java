package com.myapplicationdev.android.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterPhoto extends ArrayAdapter<Photo> {
	Context context;
	ArrayList<Photo> photos;
	int resource;
	TextView tvName,tvDes;

	public CustomAdapterPhoto(Context context, int resource, ArrayList<Photo> photos) {
		super(context, resource, photos);
		this.context = context;
		this.photos = photos;
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(resource, parent, false);

		Photo photo = photos.get(position);

		String name = photo.getName();
        String description = photo.getDescription();
		tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDes = (TextView) rowView.findViewById(R.id.tvDes);

		tvName.setText(name);
		tvDes.setText(description);

		return rowView;
	}
}
