package com.example.healthyheroes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPastSessionActivity extends Activity {

private ViewPastSessionActivity vpsa = this;
private EditText edFileName, edContent;
private Button btnSave;

private ListView listSavedFiles;

private String[] SavedFiles;			// THIS IS THE LIST OF FILES

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
      SavedFiles = getApplicationContext().fileList();
  }
  
  public void onClick(View arg0) {
	  // TODO Auto-generated method stub
  }

  // This method displays a list of saved files
  private void ShowSavedFiles() {
	  SavedFiles = getApplicationContext().fileList();
	  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		   android.R.layout.simple_list_item_1, SavedFiles);

	  OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos,
				long id) {
			TextView tv = (TextView) parent.getChildAt(pos);
			String text = tv.getText().toString();
			Log.v("CLICKED ON ITEM", text);
			Intent i = new Intent(parent.getContext(), PastSessionsActivity.class);
			startActivity(i);
		}
	  };

	  listSavedFiles.setAdapter(adapter);
	  listSavedFiles.setOnItemClickListener(listener);
  }
}
