package com.example.ecocheck;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_about:    // open about screen
			
			Intent intent = new Intent(About.this, About.class);
		    startActivity(intent);
			return true;
		case R.id.action_exit:     //exit from app
			finish();
            System.exit(0);
			return true;
		case R.id.action_to_main:
			Intent intent1 = new Intent(About.this, MainActivity.class);
		    startActivity(intent1);
		    return true;
			
		default:
			return super.onOptionsItemSelected(item);
	}

}

@SuppressLint("SdCardPath")
public void onClick1(View v)
{
	Intent i = new Intent(Intent.ACTION_SEND);
	i.setData(Uri.parse("mailto:"));
	i.setType("text/plain");
	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sergey.korchagin.il@gmail.com"});
	i.putExtra(Intent.EXTRA_SUBJECT, "ecoCheck");
	i.putExtra(Intent.EXTRA_TEXT   , " sent from ecoCheck");
	try {
	    startActivity(Intent.createChooser(i, "Send mail..."));
	} catch (android.content.ActivityNotFoundException ex) {
	    Toast.makeText(About.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	}	
}

}//class
