package teamA.travel.sieuthi;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import teamA.travel.R;





public class SieuthiAdapter extends ArrayAdapter<sieuthi> {
	Activity context = null;
	int itemlayout;
	ArrayList<sieuthi> arrNews = null;

	public SieuthiAdapter(Activity context, int motSanPham, ArrayList<sieuthi> objects) {
		super(context, -1, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		//itemlayout = resource;
		arrNews = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.list_row_sieuthi, null);
		}

		ImageView imgHis = (ImageView) convertView
				.findViewById(R.id.imageViewicon);
		TextView txtten = (TextView) convertView.findViewById(R.id.txtten);
		TextView txtdiachi = (TextView) convertView.findViewById(R.id.txtdiachi);
		TextView txtsdt = (TextView) convertView.findViewById(R.id.txtsdt);
		sieuthi st = getItem(position);
		if (st != null) {
			
			String url = st.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imgHis);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();
			
			txtten.setText(st.getName());
			txtdiachi.setText(String.valueOf(st.getDiachi()));
			txtsdt.setText(st.getSdt());
			
		}
		 
		return convertView;
	}
	
	
	//load hình lên:
	
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