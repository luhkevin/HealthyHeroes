package com.example.healthyheroes;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SellingProductView extends View {

	private Session currentSession;
	private ArrayList<FoodItem> productList;
	private Paint p;
	
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
			p = new Paint();
			//TODO initialize productList
	}
	
	@Override
	public void onDraw(Canvas c) {
		// TODO draw the products
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		int action = e.getAction(); // down move or up
		if (action == MotionEvent.ACTION_UP) 
		{
			//TODO figure out if a + or - has been pressed
		}
		
		
		return true; //handled correctly
	}
	

}
