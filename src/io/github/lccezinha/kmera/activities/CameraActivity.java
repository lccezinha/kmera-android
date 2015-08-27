package io.github.lccezinha.kmera.activities;

import java.io.File;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class CameraActivity extends Activity {
	private static final int TAKE_PICTURE = 1;
	private Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
	}
	
	public void takePicture(View view){
		if(!cameraPresent()) return;
		
		File directory = getDirectory();
		String imageName = generateFileName(directory);
		File imageFile = new File(imageName);
		
		uri = Uri.fromFile(imageFile);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		startActivityForResult(intent, TAKE_PICTURE);
	}
	
	public void showPicture(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "image/jpeg");
		startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == TAKE_PICTURE){
			if(resultCode == RESULT_OK){
				showMessage("Imagem capturada!");
				addImageToGallery();
			}else{
				showMessage("Imagem não capturada!");
			}
		}
	}
	
	private boolean cameraPresent(){
		boolean cameraPresent = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
		
		return cameraPresent;
	}
	
	private File getDirectory(){
		return Environment.
					getExternalStoragePublicDirectory(
							Environment.DIRECTORY_PICTURES);
	}
	
	private String generateFileName(File directory){
		return directory.getPath() + "/" + System.currentTimeMillis() + ".jpg";
	}
	
	private void addImageToGallery(){
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intent.setData(uri);
		this.sendBroadcast(intent);
	}
	
	private void showMessage(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	
}
