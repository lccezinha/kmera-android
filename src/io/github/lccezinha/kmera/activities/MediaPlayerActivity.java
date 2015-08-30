package io.github.lccezinha.kmera.activities;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MediaPlayerActivity extends Activity {
	private MediaPlayer mediaPlayer;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_player);
	}
	
	public void executeMusicFromFile(View view){
		mediaPlayer = MediaPlayer.create(this, R.raw.megadeth);
		mediaPlayer.start();
	}
	
	public void play(View view){
		if(!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}
	
	public void stop(View view){
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
	}
	
	public void pause(View view){
		if(mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		flushMemory();
	}
	
	private void flushMemory(){
		if(mediaPlayer == null)
			mediaPlayer.release();
	}
}
