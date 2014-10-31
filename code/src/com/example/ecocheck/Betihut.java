package com.example.ecocheck;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Betihut extends Activity {
	

	SoundPool soundPool;
	AssetManager assets;
	int butS;
	Context context;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.betihut);
	        

			context= this;
			soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
			assets = getAssets();
			butS = loadSound("1499.mp3");
	    }
	 
	 public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		
	public boolean onOptionsItemSelected(MenuItem item) {
			
			switch (item.getItemId()) {
			case R.id.action_about:    // open about screen
				
				Intent intent = new Intent(Betihut.this, About.class);
			    startActivity(intent);
				return true;
			case R.id.action_exit:     //exit from app
				finish();
	            System.exit(0);
				return true;
			case R.id.action_to_main:
				Intent intent1 = new Intent(Betihut.this, MainActivity.class);
			    startActivity(intent1);
			    return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	
	
	@SuppressLint("SdCardPath")
	public void onClickBetihut(View v) {
		String idB=(String) v.getTag();
		String  outFile = "/sdcard/NG/"+idB+".pdf";
		playSound(butS);
   	 Toast.makeText(getApplicationContext(), 
                "Opening File", Toast.LENGTH_LONG).show();
   	File file = new File(outFile);

       if (file.exists()) {
           Uri path = Uri.fromFile(file);
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setDataAndType(path, "application/pdf");
           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

           try {
           	
               startActivity(intent);
               
           } 
           catch (ActivityNotFoundException e) {
               Toast.makeText(Betihut.this, 
                   "No Application Available to View PDF", 
                   Toast.LENGTH_SHORT).show();
           }
       }

}
	
	public void playSound( int sound ) {
	    // TODO Auto-generated method stub
	    if ( sound > 0 )
	        soundPool.play(sound, 1, 1, 1, 0, 1);
	}

	public int loadSound(String fileName) {
	    // TODO Auto-generated method stub
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
}//class
