package com.example.healthyheroes;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

	private int layoutResourceId;
	private Context context;
	private List<FoodItem> items;

	public FoodItemAdapter(Context context, int resource, List<FoodItem> objects) {
		super(context, resource, objects);
		this.layoutResourceId = resource;
		this.context = context;
		this.items = objects;
	}

	Activity bob;
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		FoodItemHolder holder = null;

		if(row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new FoodItemHolder();
			holder.minus = (Button) row.findViewById(R.id.minusButton);
			holder.minus.setTag(holder);
			holder.plus = (Button) row.findViewById(R.id.plusButton);
			holder.plus.setTag(holder);
			holder.name = (TextView) row.findViewById(R.id.productName);
			holder.numberSoldLabel = (TextView) row.findViewById(R.id.numberSoldLabel);
			holder.numberSold = (TextView) row.findViewById(R.id.numberSold);
			holder.food = items.get(position);
			row.setTag(holder);
		} else {
			holder = (FoodItemHolder) row.getTag();
		}
		
		FoodItem food = holder.food;
		holder.name.setText(food.getName()); //set Name
		holder.numberSold.setText(Integer.toString(food.getNumberSold()));
		
		
		
		
		holder.minus.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				FoodItemHolder holder = (FoodItemHolder) v.getTag(); //finds holder set earlier
				FoodItem food = holder.food;
				food.decrementNumberSold();
				holder.numberSold.setText(Integer.toString(food.getNumberSold()));
			}
		}); 
		
		
		holder.plus.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				FoodItemHolder holder = (FoodItemHolder) v.getTag();
				FoodItem food = holder.food;
				food.incrementNumberSold();
				holder.numberSold.setText(Integer.toString(food.getNumberSold()));
				
			}
		}); 

		return row;
	}

	public static class FoodItemHolder {
		FoodItem food;
		TextView name;
		Button plus;
		Button minus;
		TextView numberSoldLabel;
		TextView numberSold;
	}
}
