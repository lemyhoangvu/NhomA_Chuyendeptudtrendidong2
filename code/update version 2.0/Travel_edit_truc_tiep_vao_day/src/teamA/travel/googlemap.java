package teamA.travel;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class googlemap extends Activity {

	// Khai báo đối tượng Google Map
	GoogleMap map;

	// Khai báo Progress Bar dialog để làm màn hình chờ
	ProgressDialog myProgress;

	//
	Spinner spinner_maps_type = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);

		// Tạo Progress Bar
		myProgress = new ProgressDialog(this);
		myProgress.setTitle("Đang tải Map...");
		myProgress.setMessage("Vui lòng chờ...");
		myProgress.setCancelable(true);

		// Hiển thị Progress Bar
		myProgress.show();

		// Lấy đối tượng Google Map ra:
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		// thiết lập sự kiện đã tải Map thành công
		map.setOnMapLoadedCallback(new OnMapLoadedCallback() {

			@Override
			public void onMapLoaded() {
				// Đã tải thành công thì tắt Dialog Progress đi
				myProgress.dismiss();
			}
		});

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.setMyLocationEnabled(true);

		// Thêm dòng lệnh này
		TuiDangODau();

		//
		map.getUiSettings().setIndoorLevelPickerEnabled(true);

		// nghiêng bản đồ bằng hai ngón tay đặt lên bản đồ di chuyển lên xuống
		// cùng nhau
		map.getUiSettings().setTiltGesturesEnabled(true);

		// xoay bản đồ
		map.getUiSettings().setRotateGesturesEnabled(true);

		// di chuyển
		map.getUiSettings().setScrollGesturesEnabled(true);

		// zoom
		map.getUiSettings().setZoomGesturesEnabled(true);

		// compass
		map.getUiSettings().setCompassEnabled(true);

		// chọn kiểu bản đồ
		spinner_maps_type = (Spinner) findViewById(R.id.spinner_map_type);
		String arrMap[] = getResources().getStringArray(R.array.maps_type);
		ArrayAdapter<String> adapterMap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrMap);
		adapterMap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_maps_type.setAdapter(adapterMap);
		spinner_maps_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				int type = GoogleMap.MAP_TYPE_NORMAL;
				switch (arg2) {
				case 0:
					type = GoogleMap.MAP_TYPE_NONE;
					break;
				case 1:
					type = GoogleMap.MAP_TYPE_NORMAL;
					break;
				case 2:
					type = GoogleMap.MAP_TYPE_SATELLITE;
					break;
				case 3:
					type = GoogleMap.MAP_TYPE_TERRAIN;
					break;
				case 4:
					type = GoogleMap.MAP_TYPE_HYBRID;
					break;
				}
				map.setMapType(type);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		spinner_maps_type.setSelection(1);

		//

	}

	private void TuiDangODau() {

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
		if (lastLocation != null) {
			LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
			// map.animateCamera(CameraUpdateFactory.newLatLngZoom(new
			// LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()),
			// 15));

			CameraPosition cameraPosition = new CameraPosition.Builder()
					// Sets the center of the map to location user
					.target(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()))
					// Sets the zoom
					.zoom(15)
					// Sets the orientation of the camera to east
					.bearing(90)
					// Sets the tilt of the camera to 30 degrees
					.tilt(40)
					// Creates a CameraPosition from the builder
					.build();
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

			// Thêm MarketOption cho Map:
			MarkerOptions option = new MarkerOptions();
			option.position(latLng);
			option.title("Định vị");
			option.snippet("Vị trí của bạn...");
			option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
			Marker currentMarker = map.addMarker(option);

			ImageLoadTask imgTask = new ImageLoadTask(this,
					"https://scontent-a-lax.xx.fbcdn.net/hphotos-xpa1/v/t1.0-9/1488744_806006112761224_104751868_n.jpg?oh=18c334e98bdbc3454a0b72be9dc3f7dc&oe=55417543",
					map, currentMarker);
			imgTask.execute();
		}
	}

}
