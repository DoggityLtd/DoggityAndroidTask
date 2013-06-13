package com.ros.doggity.net;

import java.util.ArrayList;

import com.ros.doggity.model.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Ros Stepanyak on 6/12/13.
 */
public class GetAllVenuesResponse extends SDResult {

    ArrayList<Venue> allVenues;
    public void parseResponseData(String data)
    {
        try {
            JSONObject jsonVenues = new JSONObject(data);
            JSONObject jsonVenusHolder = jsonVenues.getJSONObject("Venues");
            JSONArray jsonArrayOfVenues = jsonVenusHolder.getJSONArray("Venue");
            if(jsonArrayOfVenues != null) {
                if(jsonArrayOfVenues.length() > 0) {
                    allVenues = new ArrayList<Venue>();
                }
            }

            for(int i=0; i<jsonArrayOfVenues.length(); i++) {
                Venue venue = new Venue();

                /* name of the venue*/
                JSONObject jsonVenue = jsonArrayOfVenues.getJSONObject(i);
                if(jsonVenue != null) {
                    venue.setName(jsonVenue.getString("name"));
                    allVenues.add(venue);
                }

                // Address value
                if(jsonVenue.has("line1")) {
                    venue.setAddress(jsonVenue.getString("line1"));
                }


                //Max dog size allowed value
                if(jsonVenue.has("maxDogSize")) {
                    venue.setAddress(jsonVenue.getString("maxDogSize"));
                }

                /* extracting pictures */
                if(jsonVenue.has("Picture")) {
                    // extracting image urls
                    JSONArray jsonPictures = jsonVenue.getJSONArray("Picture");
                    if(jsonPictures != null) {
                        if(jsonPictures.length() > 0) {
                            for(int j = 0; j<jsonPictures.length(); j++) {
                                JSONObject jsonPictureHolder = jsonPictures.getJSONObject(j);
                                String pictureURL = jsonPictureHolder.getString("value");

                                venue.addPictureURL(pictureURL);

                            }
                        }

                    }
                }



            }
        }
        catch(JSONException e) {
           e.printStackTrace();
        }

    }

    public  ArrayList<Venue> getAllVenues() {
        return allVenues;
    }
}
