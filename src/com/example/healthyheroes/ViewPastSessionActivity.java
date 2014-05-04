package com.example.healthyheroes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewPastSessionActivity extends Activity {

private ListView listSavedFiles;

private List<String> SavedFiles;			// THIS IS THE LIST OF FILES

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_view_past_session);
      //edFileName = (EditText)findViewById(R.id.filename);
      //edContent = (EditText)findViewById(R.id.content);
      //btnSave = (Button)findViewById(R.id.save);
      listSavedFiles = (ListView)findViewById(R.id.list);
      ShowSavedFiles();
      //btnSave.setOnClickListener(new Button.OnClickListener(){
      SavedFiles = Arrays.asList(getApplicationContext().fileList());
  }
  
  public void onClick(View arg0) {
	  // TODO Auto-generated method stub
  }

  // This method displays a list of saved files
  private void ShowSavedFiles() {
	  SavedFiles = Arrays.asList(getApplicationContext().fileList());
	  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		   android.R.layout.simple_list_item_1, SavedFiles);

	  OnItemClickListener listener = new OnItemClickListener() {

	  @Override
	  public void onItemClick(AdapterView<?> parent, View view, int pos,
				long id) {
			TextView tv = (TextView) parent.getChildAt(pos);
			if(tv == null) { 
				Log.v("NULL TEXTVIEW", "null textview");
			} else {
				Log.v("GOOD TEXTVIEW", "good");
			}
			String text = tv.getText().toString();
			Log.v("CLICKED ON ITEM", text);
			Intent i = new Intent(parent.getContext(), PastSessionsActivity.class);
			i.putExtra("filename", text);
			startActivity(i);
		}
	  };
	  

	  OnItemLongClickListener longlistener = new OnItemLongClickListener() {
		  @Override
		  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
			SavedFiles.remove(position);
			TextView tv = (TextView) parent.getChildAt(position);
			return false;
		  }
	  };

	  listSavedFiles.setAdapter(adapter);
	  listSavedFiles.setOnItemClickListener(listener);
	  listSavedFiles.setOnItemLongClickListener(longlistener);
  }
}
