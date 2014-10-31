package com.example.ecocheck;


import java.io.File;
import java.io.IOException;

import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	SoundPool soundPool;
	AssetManager assets;
	int butS;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context= this;
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		assets = getAssets();
		butS = loadSound("1499.mp3");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//open betihut screen
	public void onClick1(View v)
	{
		 playSound(butS);
	    Intent intent = new Intent(MainActivity.this, Betihut.class);
	    startActivity(intent);
	}
	//open giut screen
	public void onClick2(View v)
	{
		playSound(butS);
	    Intent intent = new Intent(MainActivity.this, Giut.class);
	    startActivity(intent);
	}
	//open dropbox file
	public void onClick3(View v)
	{
		playSound(butS);
	 Uri uri = Uri.parse("https://www.dropbox.com/sh/5hebqzkr3248qv2/Mx5Hs-AlTy");
   	 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
   	 startActivity(intent);	
	}
	//send email
	@SuppressLint("SdCardPath")
	public void onClick4(View v)
	{
		playSound(butS);
		File file = new File("/sdcard/ecoCheck2.jpg");
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setData(Uri.parse("mailto:"));
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@ecocheck.co.il"});
		i.putExtra(Intent.EXTRA_SUBJECT, "from application");
		i.putExtra(Intent.EXTRA_TEXT   , " sent from info application");
		i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}	
	}
	//opens giut info screen
	public void onClick5(View v)
	{
		playSound(butS);
	    Intent intent = new Intent(MainActivity.this, Giut_info.class);
	    startActivity(intent);
	}
	
	//opens good data screen
		public void onClick6(View v)
		{
			playSound(butS);
		    Intent intent = new Intent(MainActivity.this, GoodData.class);
		    startActivity(intent);
		}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.action_about:    // open about screen
			Intent intent = new Intent(MainActivity.this, About.class);
		    startActivity(intent);
			return true;
		case R.id.action_exit:     //exit from app
			finish();
            System.exit(0);
			return true;
		case R.id.action_to_main:
			Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
		    startActivity(intent1);
		    return true;
		default:
			return super.onOptionsItemSelected(item);
	}
	
	}
	
	 public void playSound( int sound ) {
	        if ( sound > 0 )
	            soundPool.play(sound, 1, 1, 1, 0, 1);
	    }
	 
	    public int loadSound(String fileName) {
	        AssetFileDescriptor aFileDescriptor = null;
	        try {
	            aFileDescriptor = assets.openFd(fileName);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	            Toast.makeText( this, "Could't load file"+ fileName, Toast.LENGTH_SHORT).show();
	            return -1;
	        }
	        return soundPool.load(aFileDescriptor, 1);
	    }
}
