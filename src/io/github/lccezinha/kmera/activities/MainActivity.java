package io.github.lccezinha.kmera.activities;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void selectOption(View view){
		Intent intent = null;
		
		switch (view.getId()) {
		case R.id.camera:
			intent = new Intent(this, CameraActivity.class);
			break;

		case R.id.media_player:
			intent = new Intent(this, MediaPlayerActivity.class);
			break;
		}
		
		startActivity(intent);
	}
	
}
