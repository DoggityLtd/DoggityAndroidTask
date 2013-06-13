package com.ros.doggity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.ros.doggity.R;
import com.ros.doggity.activity.auxiliary.LazyAdapter;
import com.ros.doggity.model.Venue;
import com.ros.doggity.net.GetAllVenuesRequest;
import com.ros.doggity.net.GetAllVenuesResponse;
import com.ros.doggity.net.ResponseReciever;
import com.ros.doggity.net.SDDispatcher;
import com.ros.doggity.net.SDResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends Activity {
    class VenuesReciever implements  ResponseReciever {
        public void handleResponseData(SDResult response) {
            populateVenues(response);
        }
    };
    ListView list;
    LazyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

//        try {
//        	Log.d("Shit", "Fetching data");
//        	downloadUrl("http://api.dev.friendbuy.de/venue/location");
//        }
//        catch(IOException e) {
//
//        }


        ResponseReciever venuesHandler = new VenuesReciever();
        GetAllVenuesRequest getAllVenuesRequest = new GetAllVenuesRequest(venuesHandler);

        SDDispatcher.sharedInstance().processRequest(getAllVenuesRequest);
        
        Button b=(Button)findViewById(R.id.button1);
        b.setOnClickListener(listener);
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
        adapter=new LazyAdapter(this, venues);
        list.setAdapter(adapter);
    }
    
    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        String result = "empty"; 
        try {
            URL url = new URL(myurl);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(myurl);

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("range", "25000000"));
                nameValuePairs.add(new BasicNameValuePair("latitude", "52.5295667"));
                nameValuePairs.add(new BasicNameValuePair("longitude", "13.4564908"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();

                result = convertStreamToString(instream);

            }

          }
            catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }


        }
        finally {
            if (is != null) {
                is.close();
            } 
        }
        Log.i("Shit", result); 
        return result;
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