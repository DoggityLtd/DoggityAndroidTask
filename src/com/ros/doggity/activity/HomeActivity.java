package com.ros.doggity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ros.doggity.R;
import com.ros.doggity.activity.auxiliary.LazyAdapter;
import com.ros.doggity.model.Venue;
import com.ros.doggity.net.RequestsAndResponses.GetAllVenuesRequest;
import com.ros.doggity.net.RequestsAndResponses.GetAllVenuesResponse;
import com.ros.doggity.net.ResponseReciever;
import com.ros.doggity.net.SDDispatcher;
import com.ros.doggity.net.SDResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class HomeActivity extends Activity {
    class VenuesReciever implements  ResponseReciever {
        public void handleResponseData(SDResult response) {
            populateVenues(response);
        }
    };

    private static String FB_INTENT_ID = "facebook.action.login";
    ListView list;
    LazyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // fetching venues
        ResponseReciever venuesHandler = new VenuesReciever();
        GetAllVenuesRequest getAllVenuesRequest = new GetAllVenuesRequest(venuesHandler);

        SDDispatcher.sharedInstance().processRequest(getAllVenuesRequest);

    }
    
    @Override
    public void onDestroy()
    {
        list.setAdapter(null);
        super.onDestroy();
    }

    public void populateVenues(SDResult resultVenues) {
         ArrayList<Venue> venues = ((GetAllVenuesResponse)resultVenues).getAllVenues();

         list=(ListView)findViewById(R.id.list);
         list.setClickable(true);


         list.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    launchLoginActivity();
            }
        });
         adapter=new LazyAdapter(this, venues);
         list.setAdapter(adapter);
    }

    public void launchLoginActivity() {
        Intent intent = new Intent(this, FacebookLoginActivity.class);
      //  String message = "dummy string";
       // intent.putExtra(FB_INTENT_ID, message);
        startActivity(intent);
    }


    public String convertStreamToString(InputStream inputStream) throws IOException {
    	if (inputStream != null) {
    		StringBuilder sb = new StringBuilder();
    		String line;
    		try {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    			while ((line = reader.readLine()) != null) {
    				sb.append(line).append("\n");
    			}
    		} finally {
    			inputStream.close();
    		}
    		return sb.toString();
    	} else {
    		return "";
    	}
    }        
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");        
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
    
}