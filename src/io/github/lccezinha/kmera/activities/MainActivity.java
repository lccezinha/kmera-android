package io.github.lccezinha.kmera.activities;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void selectOption(View view){
		Log.e("Main", view.getId() + "");
		
		switch (view.getId()) {
		case R.id.camera:
			startActivity(new Intent(this, CameraActivity.class));
			break;

		case R.id.mediaPlayer:
			startActivity(new Intent(this, MediaPlayerActivity.class));
			break;
			
		case R.id.location:
			startActivity(new Intent(this, LocationActivity.class));
		}
	}	
}
