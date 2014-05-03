package com.example.healthyheroes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PastSessionsActivity extends Activity {
	private String logfileName;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("PSA", "Reached past sessions activities screen");
		Intent i = this.getIntent();
		logfileName = i.getStringExtra("filename");
		Log.v("PSA", logfileName);
		getFileData();
    }
	
	private void getFileData() {
		//Get the file and read from it
		StringBuilder logfileText = new StringBuilder();
		try {
		  InputStream fin = openFileInput(logfileName);
		  if(fin != null) {
			  BufferedReader buf = new BufferedReader(new InputStreamReader(fin));
			  String data = "";
			  while((data = buf.readLine()) != null) {
				  logfileText.append(data);
				  logfileText.append("\n");
			  }
			  Log.v("PSA", logfileText.toString());
			  fin.close();		  
		  }
		} catch (Exception e) {
			  Log.v("PSA", "didn't read");
		  e.printStackTrace();
		}	
		
		RelativeLayout rView = new RelativeLayout(this);
	    TextView myText = new TextView(this);
	    myText.setText(logfileText);
	    rView.addView(myText);
	    setContentView(rView);
	}
}
