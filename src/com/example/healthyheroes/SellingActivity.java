package com.example.healthyheroes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SellingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selling, menu);
        return true;
    }
    
    public void onFinishButton(View v) {
    	//TODO write me
    }
    
    public void onBackButton(View v) {
    	//TODO write me
    }
}
