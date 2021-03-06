package io.github.lccezinha.kmera.activities;

import io.github.lccezinha.kmera.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class LocationActivity extends Activity {
	private LocationManager locationManager;
	private TextView provider, longitude, latitude;
	private WebView map;
	private String urlBase = "http://maps.googleapis.com/maps/api" +
		"/staticmap?size=400x400&sensor=true" +
		" &markers=color:red|%s,%s";
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		
		initializeViewComponents();
		
		locationManager = (LocationManager) 
				this.getSystemService(Context.LOCATION_SERVICE);
		
		Listener listener = new Listener();
		long interval = 0;
		float distance = 0;
		
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER,
				interval, distance, listener);
		
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				interval, distance, listener);
	}
	
	private class Listener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			String latitudeStr = 
					String.valueOf(location.getLatitude());
			String longitudeStr =
					String.valueOf(location.getLongitude());
			String url = 
					String.format(urlBase, latitudeStr, longitudeStr);
			
			provider.setText(location.getProvider());
			latitude.setText(latitudeStr);
			longitude.setText(longitudeStr);
			
			map.loadUrl(url);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}

		@Override
		public void onProviderEnabled(String provider) {}

		@Override
		public void onProviderDisabled(String provider) {}
	}
	
	private void initializeViewComponents(){
		provider = (TextView) findViewById(R.id.provider);
		latitude = (TextView) findViewById(R.id.latitude);
		longitude = (TextView) findViewById(R.id.longitude);
		map = (WebView) findViewById(R.id.map);
	}
}
