package teamA.travel.lehoi;


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

public class Lehoi_Activity extends Activity {
	
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
	
    ListView listViewlehoi;
    ArrayList<Lehoi> dsLH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lehoi_layout);
        
       
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
        
        
        listViewlehoi = (ListView)findViewById(R.id.listViewlehoi);
        dsLH = new ArrayList<Lehoi>();
     
        
 //lấy data khi click item trong listview:
        listViewlehoi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				Intent i1 = new Intent(getApplicationContext(), Chitiet_lehoi.class);
            	Lehoi lh = (Lehoi) parent.getItemAtPosition(position);
            	Bundle b = new Bundle();
            	b.putSerializable("ct_lh", lh);
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
        
        
        
       new docJSON().execute("https://lequangsang.000webhostapp.com/lehoi.php");
    }

    class docJSON extends AsyncTask<String, Integer, String> {

    	ProgressDialog dialog;
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Lehoi_Activity.this);
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
            dsLH = new ArrayList<Lehoi>();
            try {
                JSONArray mang = new JSONArray(s);
                for(int i = 0; i < mang.length(); i++)
                {
                    JSONObject lhoi = mang.getJSONObject(i);
                    dsLH.add(
                            new Lehoi(
                            		lhoi.getInt("lehoi_id"),
                            		lhoi.getString("lehoi_ten"),
                            		lhoi.getString("lehoi_diachi"),
                                    lhoi.getString("lehoi_thongtin"),
                                    lhoi.getString("lehoi_hinhanh"),
                                    lhoi.getString("lehoi_hinhanh1"),
                                    lhoi.getString("lehoi_hinhanh2"),
                                    lhoi.getString("lehoi_hinhanh3")
                            )
                    );
                }

              LehoiAdapter adapter = new LehoiAdapter (
            		  Lehoi_Activity.this,
                        R.layout.list_row_lehoi,
                        dsLH
                );
                
         /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, dsHH);*/
            
//Sort theo tên: 
              Collections.sort(dsLH, new Comparator<Lehoi>() {

				@Override
				public int compare(Lehoi lhs, Lehoi rhs) {
					// TODO Auto-generated method stub
					return lhs.getName().compareTo(rhs.getName());
				}
            	  
              });
             
                
                listViewlehoi.setAdapter(adapter);
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