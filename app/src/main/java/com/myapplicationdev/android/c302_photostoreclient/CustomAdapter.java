package com.myapplicationdev.android.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Category> {
	Context context;
	ArrayList<Category> categories;
	int resource;
	TextView tvName,tvDes;

	public CustomAdapter(Context context, int resource, ArrayList<Category> categories) {
		super(context, resource, categories);
		this.context = context;
		this.categories = categories;
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(resource, parent, false);

		Category category = categories.get(position);

		String name = category.getName();
        String description = category.getDescription();
		tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDes = (TextView) rowView.findViewById(R.id.tvDes);

		tvName.setText(name);
		tvDes.setText(description);

		return rowView;
	}
}
