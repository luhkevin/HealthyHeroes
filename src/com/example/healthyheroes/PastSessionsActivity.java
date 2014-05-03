package com.example.healthyheroes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class PastSessionsActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("PSA", "Reached past sessions activities screen");
    }
}
