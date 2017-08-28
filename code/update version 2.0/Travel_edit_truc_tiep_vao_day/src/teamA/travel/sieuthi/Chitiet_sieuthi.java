package teamA.travel.sieuthi;


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


public class Chitiet_sieuthi extends Activity {
	TextView textViewdc, textViewsdt,textViewmota, textViewsdt1;
	ImageView imagesieuthi;
	Button buttonlienhe, buttonshare;
	private Dialog dialog;
	private String sdt = "";
	private String diachi = "";
	private String mota = "";
	private String ten = "";
	private String url = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chitiet_sieuthi);
		
		textViewdc = (TextView) findViewById(R.id.textViewdc);
		textViewsdt = (TextView) findViewById(R.id.textViewsdt);
		textViewmota = (TextView) findViewById(R.id.textViewmota);
		imagesieuthi = (ImageView) findViewById(R.id.imagesieuthi);
		buttonlienhe = (Button) findViewById(R.id.buttonlienhe);
		buttonshare = (Button) findViewById(R.id.buttonshare);
		
		Bundle b = getIntent().getExtras();
		if(b != null) {
			sieuthi st = (sieuthi) b.getSerializable("ct_st");
			
			ten = st.getName();
			
			diachi = st.getDiachi();
			textViewdc = (TextView)findViewById(R.id.textViewdc);
			textViewdc.setText(diachi);
			
			sdt = st.getSdt();
			textViewsdt = (TextView)findViewById(R.id.textViewsdt);
			textViewsdt.setText(sdt);
			
		
			mota = st.getMota();
			textViewmota = (TextView)findViewById(R.id.textViewmota);
			textViewmota.setText(mota);
			
			url = st.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imagesieuthi);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();
			
			
}
		
		
		//Thuc hien cuoc goi khi bam button lien he:
		
		buttonlienhe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 // khởi tạo dialog
				dialog = new Dialog(Chitiet_sieuthi.this);
               
				  // xét layout cho dialog
                dialog.setContentView(R.layout.dialog_lienhe);
               
              //Tieu de cho dialog
                dialog.setTitle("Xác Nhận");
              
                //
                TextView txt = (TextView) dialog.findViewById(R.id.textViewsdt1);
                txt.setText(sdt);
 
                Button dialogButton = (Button) dialog.findViewById(R.id.buttoncall);
                Button buttoncancel = (Button) dialog.findViewById(R.id.buttoncancel);
                
                //bắt sự kiện khi bấm Call:
                dialogButton.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try {
		    				String phoneNumber = textViewsdt.getText().toString();
		    			 
		    					Intent it = new Intent(Intent.ACTION_CALL);
		    					it.setData(Uri.parse("tel:" + phoneNumber));
		    					startActivity(it);
		    				}
		    				catch (ActivityNotFoundException e) {
		    					// Exceptions
		    				}
					}
				});
                
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
		
		
		//share:
		buttonshare.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	            
	            sharingIntent.setType("text/plain");
	            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "chia se");
	        sharingIntent.putExtra(Intent.EXTRA_TEXT, ten + " \n" + " \n" + "Địa Chỉ:" + diachi + " \n" + " \n" + "Tel:" + sdt + " \n" + " \n" + "Thông Tin:" + mota + " \n" + " \n" + url);
	         //   sharingIntent.putExtra(Intent.EXTRA_TEXT, "online.tdc.edu.vn");
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