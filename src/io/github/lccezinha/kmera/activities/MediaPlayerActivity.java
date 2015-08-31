package io.github.lccezinha.kmera.activities;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MediaPlayerActivity extends Activity
		implements OnPreparedListener {
	
	private MediaPlayer mediaPlayer;
	
	public void onCreate(Bundle savedInstanceState){
		Log.e("Dentro do MediaPlayer", "WTF ?");
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
	
	public void executeMusicFromUrl(View view){
		flushMemory();
		mediaPlayer = new MediaPlayer();
		
		try{
			String urlToStream = "";
			Uri uri = Uri.parse(urlToStream);
			mediaPlayer.setDataSource(this, uri);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.prepareAsync();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();
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
