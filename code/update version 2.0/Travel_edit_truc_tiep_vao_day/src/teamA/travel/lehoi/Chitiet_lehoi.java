package teamA.travel.lehoi;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import teamA.travel.R;


public class Chitiet_lehoi extends Activity {
	TextView textViewdc,textViewmota, textViewsdt1;
	ImageView imagesieuthi, imageView1, imageView2, imageView3;
	Button buttonlienhe, buttonshare;
//	private Dialog dialog;
	private String diachi = "";
	private String mota = "";
	private String ten = "";
	private String url = "";
	private String url1 = "";
	private String url2 = "";
	private String url3 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chitiet_lehoi);
		
		textViewdc = (TextView) findViewById(R.id.textViewdc);
		textViewmota = (TextView) findViewById(R.id.textViewmota);
		imagesieuthi = (ImageView) findViewById(R.id.imagesieuthi);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
//		buttonlienhe = (Button) findViewById(R.id.buttonlienhe);
		buttonshare = (Button) findViewById(R.id.buttonshare);
		
		Bundle b = getIntent().getExtras();
		if(b != null) {
			Lehoi lh = (Lehoi) b.getSerializable("ct_lh");
			
			ten = lh.getName();
			
			diachi = lh.getDiachi();
			textViewdc = (TextView)findViewById(R.id.textViewdc);
			textViewdc.setText(diachi);
			
		
			mota = lh.getMota();
			textViewmota = (TextView)findViewById(R.id.textViewmota);
			textViewmota.setText(mota);
			
			url = lh.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imagesieuthi);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();
			
			url1 = lh.getHinh1();
			ImageDownloadMessageHandler ImageloadHandler1 = new ImageDownloadMessageHandler(imageView1);
			GetImageThread LoadImageThread1 = new GetImageThread(ImageloadHandler1, url1);
			LoadImageThread1.start();
			
			url2 = lh.getHinh2();
			ImageDownloadMessageHandler ImageloadHandler2 = new ImageDownloadMessageHandler(imageView2);
			GetImageThread LoadImageThread2 = new GetImageThread(ImageloadHandler2, url2);
			LoadImageThread2.start();
			
			url3 = lh.getHinh3();
			ImageDownloadMessageHandler ImageloadHandler3 = new ImageDownloadMessageHandler(imageView3);
			GetImageThread LoadImageThread3 = new GetImageThread(ImageloadHandler3, url3);
			LoadImageThread3.start();
			
			
}
		
		
		//Thuc hien cuoc goi khi bam button lien he:
	
/*		buttonlienhe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 // khởi tạo dialog
				dialog = new Dialog(Chitiet_lehoi.this);
               
				  // xét layout cho dialog
                dialog.setContentView(R.layout.dialog_lienhe);
               
              //Tieu de cho dialog
                dialog.setTitle("Xác Nhận");
              
 
                Button dialogButton = (Button) dialog.findViewById(R.id.buttoncall);
                Button buttoncancel = (Button) dialog.findViewById(R.id.buttoncancel);
                
                
                //bắt sự kiện khi bấm Cancel:
                buttoncancel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
                
                
    
                dialog.show();
                // hiển thị dialog
				
				
				
				
			}
		});
	*/
		
		//share:
		buttonshare.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	            
	            sharingIntent.setType("text/plain");
	            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "chia se");
	        sharingIntent.putExtra(Intent.EXTRA_TEXT, ten + " \n" 
	            + " \n" + "Địa Chỉ:" + diachi
	            + " \n" + " \n" + "Thông Tin:" + mota 
	            + " \n" + " \n" + url
	            + "\n" + "\n" + url1
	            + "\n" + "\n" + url2
	            + "\n" + "\n" + url3);
	            startActivity(sharingIntent);
	         }
	      });
		
}
	

	
	
	
	
	
	
	class GetImageThread extends Thread {
		ImageDownloadMessageHandler mGetImageHandler;
		String mUrl;

		public GetImageThread(ImageDownloadMessageHandler getImageHandler,
				String ImageUrl) {
			this.mGetImageHandler = getImageHandler;
			this.mUrl = ImageUrl;
		}

		@Override
		public void run() {
			Drawable drawable = LoadImageFromWeb(mUrl);
			Message message = mGetImageHandler.obtainMessage(1, drawable);
			mGetImageHandler.sendMessage(message);
			System.out.println("Message sent");
		}
	}

	class ImageDownloadMessageHandler extends Handler {
		View imageTextView;

		public ImageDownloadMessageHandler(View imageTextView) {
			this.imageTextView = imageTextView;
		}

		@Override
		public void handleMessage(Message message) {
			imageTextView.setBackgroundDrawable(((Drawable) message.obj));
			imageTextView.setVisibility(View.VISIBLE);
		}
	}

	private Drawable LoadImageFromWeb(String url) {
		Drawable d = null;
		InputStream is = null;
		try {
			is = (InputStream) new URL(url).getContent();
			d = Drawable.createFromStream(is, "src name");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
}