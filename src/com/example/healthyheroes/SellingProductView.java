package com.example.healthyheroes;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class SellingProductView extends View {

	private Session currentSession;
	private ArrayList<FoodItem> productList;
	
	public SellingProductView(Context context, Session s) {
		super(context);
		init(s);
	}
	public SellingProductView(Context context, AttributeSet attrs, Session s) {
		super(context, attrs);
		init(s);
	}
	private void init(Session s) {
			currentSession = s;
			//TODO initialize productList
	}
	
	
	
	

}
