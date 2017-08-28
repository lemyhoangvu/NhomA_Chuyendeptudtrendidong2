package teamA.travel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindowAdapter implements InfoWindowAdapter {

	private Activity context;
	private Bitmap btmp;

	public MyInfoWindowAdapter(Activity context, Bitmap result) {
		this.context = context;
		this.btmp = result;
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub

		// Getting view from the layout file info_window_layout
		View view = this.context.getLayoutInflater().inflate(R.layout.custom_info, null);

		// Getting the position from the marker
		LatLng latLng = arg0.getPosition();

		// Getting reference to the TextView to set latitude
		TextView txtLatitude = (TextView) view.findViewById(R.id.txtLatitude);

		// Getting reference to the TextView to set longitude
		TextView txtLongitude = (TextView) view.findViewById(R.id.txtLongitude);

		TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
		TextView txtSnippet = (TextView) view.findViewById(R.id.txtSnippet);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);

		// Setting the latitude
		txtLatitude.setText("Latitude: " + latLng.latitude);

		// Setting the longitude
		txtLongitude.setText("Longitude: " + latLng.longitude);

		txtTitle.setText(arg0.getTitle());
		txtSnippet.setText(arg0.getSnippet());
		imageView.setImageBitmap(btmp);
		return view;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
