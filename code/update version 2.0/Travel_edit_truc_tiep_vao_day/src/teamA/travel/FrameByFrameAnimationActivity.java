package teamA.travel;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class FrameByFrameAnimationActivity extends Activity {

	long Delay = 2700;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_frame_by_frame_animation);
		ImageView img = (ImageView) findViewById(R.id.image);
		img.setVisibility(ImageView.VISIBLE);
		img.setBackgroundResource(R.drawable.frame_animation);
		AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
		
		frameAnimation.start();
		Timer RunSplash = new Timer();
		
		TimerTask ShowSplash = new TimerTask() {
			@Override
			public void run() {
				// Close SplashScreenActivity.class
				finish();

				// Start MainActivity.class
				Intent myIntent = new Intent(FrameByFrameAnimationActivity.this,
						MainActivity.class);
				startActivity(myIntent);
			}
			
		};
		
		RunSplash.schedule(ShowSplash, Delay);
		
		/*frameAnimation.start();*/
		/*if (frameAnimation != null) {

		}*/
		/*Intent intent = new Intent(FrameByFrameAnimationActivity.this,
				MainActivity.class);
		startActivity(intent);
*/
	}
	
	
	
	
}
