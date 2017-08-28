package teamA.travel.sieuthi;

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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import teamA.travel.R;

public class Sieuthi_Activity extends Activity {
	
	//private static final ArrayAdapter<String> List = null;

/*
	String[] actions = new String[] {
			"Táº¥t cáº£",
			"Long An",
			"Cáº§n ThÆ¡",
			"Há»“ ChÃ­ Minh",
			"BÃ¬nh DÆ°Æ¡ng",
			"ÄÃ  Náºµng"
		};
	*/
	public TextView txtten;
    ListView listViewsieuthi;
    ArrayList<sieuthi> dsST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermarket_layout);
        
       
   /*     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, actions);
        
        // Enabling dropdown list navigation for the action bar
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        // Defining Navigation listener 
        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
			
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			//	Toast.makeText(getBaseContext(), "You selected : " + actions[itemPosition]  , Toast.LENGTH_SHORT).show();
				
//lá»±a chá»n theo tá»‰nh:
				if(actions[itemPosition].toString() == "Há»“ ChÃ­ Minh"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=283");
				}
				if(actions[itemPosition].toString() == "Táº¥t cáº£"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php");
				}
				if(actions[itemPosition].toString() == "Cáº§n ThÆ¡"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=292");
				}
				if(actions[itemPosition].toString() == "BÃ¬nh DÆ°Æ¡ng"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=274");
				}
				if(actions[itemPosition].toString() == "Long An"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=272");
				}
				if(actions[itemPosition].toString() == "ÄÃ  Náºµng"){
					new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=233");
				}
				
				return false;
			}
		};
	
		/** Setting dropdown items and item navigation listener for the actionbar 
		getActionBar().setListNavigationCallbacks(adapter, navigationListener);
        
     */   
        
        txtten = (TextView) findViewById(R.id.txtten);
        listViewsieuthi = (ListView)findViewById(R.id.listViewsieuthi);
        dsST = new ArrayList<sieuthi>();
     
      
 //láº¥y data khi click item trong listview:
        listViewsieuthi.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i1 = new Intent(getApplicationContext(), Chitiet_sieuthi.class);
               	sieuthi st = (sieuthi) parent.getItemAtPosition(position);
               	Bundle b = new Bundle();
               	b.putSerializable("ct_st", st);
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
        
        
        
       new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php");
    }

    class docJSON extends AsyncTask<String, Integer, String> {

    	ProgressDialog dialog;
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Sieuthi_Activity.this);
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
            dsST = new ArrayList<sieuthi>();
            try {
                JSONArray mang = new JSONArray(s);
                for(int i = 0; i < mang.length(); i++)
                {
                    JSONObject sthi = mang.getJSONObject(i);
                    dsST.add(
                            new sieuthi(
                                    sthi.getInt("st_id"),
                                    sthi.getString("st_ten"),
                                    sthi.getString("st_diachi"),
                                    sthi.getString("st_tinh"),
                                    sthi.getString("st_sdt"),
                                    sthi.getString("st_time"),
                                    sthi.getString("st_thongtin"),
                                    sthi.getString("st_hinhanh")
                            )
                    );
                }

              SieuthiAdapter adapter = new SieuthiAdapter(
            		  Sieuthi_Activity.this,
                        R.layout.list_row_sieuthi,
                        dsST
                );
                
         /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, dsHH);*/
            
//Sort theo tÃªn: 
              Collections.sort(dsST, new Comparator<sieuthi>() {

				@Override
				public int compare(sieuthi lhs, sieuthi rhs) {
					// TODO Auto-generated method stub
					return lhs.getName().compareTo(rhs.getName());
				}
            	  
              });
             
                
                listViewsieuthi.setAdapter(adapter);
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