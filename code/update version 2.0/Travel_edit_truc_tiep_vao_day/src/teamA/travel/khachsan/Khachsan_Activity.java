package teamA.travel.khachsan;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.ActionBar.OnNavigationListener;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.Toast;
import teamA.travel.R;

public class Khachsan_Activity extends Activity {
	
	//private static final ArrayAdapter<String> List = null;

/*
	String[] actions = new String[] {
			"Tất cả",
			"Long An",
			"Cần Thơ",
			"Hồ Chí Minh",
			"Bình Dương",
			"Đà Nẵng"
		};
	*/
	
    ListView listViewkhachsan;
    ArrayList<Khachsan> dsKS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khachsan_layout);
        
       
   /*     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions);
        
        // Enabling dropdown list navigation for the action bar
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        // Defining Navigation listener 
        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			//	Toast.makeText(getBaseContext(), "You selected : " + actions[itemPosition]  , Toast.LENGTH_SHORT).show();
				
//lựa chọn theo tỉnh:
				if(actions[itemPosition].toString() == "Hồ Chí Minh"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=283");
				}
				if(actions[itemPosition].toString() == "Tất cả"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php");
				}
				if(actions[itemPosition].toString() == "Cần Thơ"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=292");
				}
				if(actions[itemPosition].toString() == "Bình Dương"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=274");
				}
				if(actions[itemPosition].toString() == "Long An"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=272");
				}
				if(actions[itemPosition].toString() == "Đà Nẵng"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=233");
				}
				
				return false;
			}
		};
	
		/** Setting dropdown items and item navigation listener for the actionbar 
		getActionBar().setListNavigationCallbacks(adapter, navigationListener);
        
     */   
        
        
        listViewkhachsan = (ListView)findViewById(R.id.listViewkhachsan);
        dsKS = new ArrayList<Khachsan>();
     
        
 //lấy data khi click item trong listview:
        listViewkhachsan.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				Intent i1 = new Intent(getApplicationContext(), Chitiet_khachsan.class);
            	Khachsan lh = (Khachsan) parent.getItemAtPosition(position);
            	Bundle b = new Bundle();
            	b.putSerializable("ct_ks", lh);
            	i1.putExtras(b);
				startActivity(i1);
				
			}
		});
        
     //   https://hienlth.info/mdata/insert.php?ma="1"&ten=""
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php");
            }
        });*/
        
        
        
       new docJSON().execute("http://appdulich.duoimuoi.vn/khachsan.php");
    }

    class docJSON extends AsyncTask<String, Integer, String> {

    	ProgressDialog dialog;
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Khachsan_Activity.this);
			dialog.setMessage("Đang xử lý. Vui lòng chờ trong giây lát...");
			dialog.show();
		}
    	
    	@Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
        	dialog.dismiss();
            dsKS = new ArrayList<Khachsan>();
            try {
                JSONArray mang = new JSONArray(s);
                for(int i = 0; i < mang.length(); i++)
                {
                    JSONObject ksan = mang.getJSONObject(i);
                    dsKS.add(
                            new Khachsan(
                            		ksan.getInt("ID_KhachSan"),
                            		ksan.getString("Name_KhachSan"),
                            		ksan.getString("Diachi_KhachSan"),
                            		ksan.getString("Dienthoai_KhachSan"),
                            		ksan.getString("Tinh_KhachSan"),
                            		ksan.getString("Fax_KhachSan"),
                            		ksan.getString("Email_KhachSan"),
                            		ksan.getString("Website_KhachSan"),
                            		ksan.getString("Mota_KhachSan"),
                            		ksan.getString("Hinhanh_KhachSan"),
                            		ksan.getString("Hinhanh2_KhachSan"),
                            		ksan.getString("Hinhanh3_KhachSan"),
                            		ksan.getString("Hinhanh4_KhachSan")
                            )
                    );
                }

              KhachsanAdapter adapter = new KhachsanAdapter (
            		  Khachsan_Activity.this,
                        R.layout.list_row_khachsan,
                        dsKS
                );
                
         /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, dsHH);*/
            
//Sort theo tên: 
              Collections.sort(dsKS, new Comparator<Khachsan>() {

				@Override
				public int compare(Khachsan lhs, Khachsan rhs) {
					// TODO Auto-generated method stub
					return lhs.getName().compareTo(rhs.getName());
				}
            	  
              });
             
                
                listViewkhachsan.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();

    }

}