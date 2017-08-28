package teamA.travel;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.Toast;
import teamA.travel.dulich.Dulich_Activity;
import teamA.travel.khachsan.Khachsan_Activity;
import teamA.travel.lehoi.Lehoi_Activity;
import teamA.travel.sieuthi.Sieuthi_Activity;

public class MainActivity extends TabActivity {

	private TabHost tabHost;
	private int currentTab = 2;
	private int lastTab = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabHost = getTabHost();

		//
		TabSpec gmSpec = tabHost.newTabSpec("Google map");
		gmSpec.setIndicator("", getResources().getDrawable(R.drawable.map));
		Intent gmIntent = new Intent(this, googlemap.class);
		gmSpec.setContent(gmIntent);

		TabSpec ddtqSpec = tabHost.newTabSpec("Địa điểm tham quan");
		ddtqSpec.setIndicator("", getResources().getDrawable(R.drawable.photo));
		Intent ddtqIntent = new Intent(this, Dulich_Activity.class);
		ddtqSpec.setContent(ddtqIntent);
		
		TabSpec ttlhSpec = tabHost.newTabSpec("Tin tức lễ hội");
		ttlhSpec.setIndicator("", getResources().getDrawable(R.drawable.carnival));
		Intent ttlhIntent = new Intent(this, Lehoi_Activity.class);
		ttlhSpec.setContent(ttlhIntent);
		
		TabSpec ksSpec = tabHost.newTabSpec("Khách sạn");
		ksSpec.setIndicator("", getResources().getDrawable(R.drawable.bed));
		Intent ksIntent = new Intent(this, Khachsan_Activity.class);
		ksSpec.setContent(ksIntent);
		
		TabSpec stSpec = tabHost.newTabSpec("Siêu thị");
		stSpec.setIndicator("", getResources().getDrawable(R.drawable.groceries));
		Intent stIntent = new Intent(this, Sieuthi_Activity.class);
		stSpec.setContent(stIntent);

		//
		tabHost.addTab(gmSpec);
		tabHost.addTab(ddtqSpec);
		tabHost.addTab(ttlhSpec);
		tabHost.addTab(ksSpec);
		tabHost.addTab(stSpec);
		
		//
		tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#35b587"));
		tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#35b587"));
		tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#35b587"));
		tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#35b587"));
		tabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#35b587"));
	
		// #35b587 53.181.135, 0e9884 14.152.132, 91dc5a 145.220.90 màu nền, đổi
		// màu f0f1f2 240.241.242
		getTabHost().setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				currentTab = getTabHost().getCurrentTab();
				setCurrentTabColor();
				lastTab = currentTab;
			}
		});
		getTabWidget().getChildAt(lastTab).setBackgroundColor(Color.rgb(53, 181, 135));
	}

	public void setCurrentTabColor() {
		getTabWidget().getChildAt(currentTab).setBackgroundColor(Color.rgb(14, 152, 132));
		getTabWidget().getChildAt(lastTab).setBackgroundColor(Color.parseColor("#35b587"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// Set up ShareActionProvider's default share intent
		// MenuItem shareItem = menu.findItem(R.id.action_share);
		// ShareActionProvider myShareActionProvider = (ShareActionProvider)
		// MenuItemCompat
		// .getActionProvider(shareItem);
		//
		// Intent myIntent = new Intent();
		// myIntent.setAction(Intent.ACTION_SEND);
		// myIntent.putExtra(Intent.EXTRA_TEXT,
		// "Đây là đoạn text tôi muốn chia sẻ");
		// myIntent.setType("text/plain");
		//
		// myShareActionProvider.setShareIntent(myIntent);
		//
		// return super.onCreateOptionsMenu(menu);
		//
		return true;
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	//
	// // noinspection SimplifiableIfStatement
	// if (id == R.id.action_settings) {
	// return true;
	// }

	// if (id == R.id.action_new) {
	// Toast.makeText(this, "icon New is clicked", Toast.LENGTH_SHORT).show();
	// }
	//
	// if (id == R.id.action_edit) {
	// Toast.makeText(this, "icon Edit is clicked", Toast.LENGTH_SHORT).show();
	// }
	//
	// if (id == R.id.action_share) {
	// Toast.makeText(this, "icon Share is clicked", Toast.LENGTH_SHORT).show();
	// }

	// return super.onOptionsItemSelected(item);
	// }
}
