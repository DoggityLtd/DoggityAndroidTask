package com.ros.doggity.net;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ros Stepanyak on 6/12/13.
 * Basic Request
 */
public class GetAllVenuesRequest extends SDRequest {

    @Override
    public HttpRequestBase serviceURLRequest()
    {
        HttpPost httppost = new HttpPost("http://api.dev.friendbuy.de/venue/location");

        try{
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("range", "25000000"));
            nameValuePairs.add(new BasicNameValuePair("latitude", "52.5295667"));
            nameValuePairs.add(new BasicNameValuePair("longitude", "13.4564908"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return httppost;
    }
    public GetAllVenuesRequest(ResponseReciever owner) {
         this.owner = owner;
    }

    public GetAllVenuesResponse emptyResponse() {
          return new GetAllVenuesResponse();
    }
}
