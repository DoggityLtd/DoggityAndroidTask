package com.ros.doggity.model;

import java.util.ArrayList;

/**
 * Created by Ros Sterpanyak on 6/12/13.
 */
public class Venue {
    String name;
    ArrayList<String> pictureURLs;
    String address;
    String maxDogSize;

    public Venue() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Venue(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setMaxDogSize(String maxDogSize) {
        this.maxDogSize = maxDogSize;
    }

    public String getMaxDogSize() {
        return maxDogSize;
    }

    public void addPictureURL(String pictureURL) {
        if(pictureURLs == null) {
            pictureURLs = new ArrayList<String>();
        }
        pictureURLs.add(pictureURL);
    }

    public ArrayList<String> getPictureURLs() {
        return pictureURLs;
    }

    /* returns 1st url, test */
    public String getPictureURLForDisplay() {
        if(pictureURLs != null) {
            if(pictureURLs.size() > 0) {
                //TODO: figure out possible number of urls and info about them
                return pictureURLs.get(0);
            }
        }
        return null;
    }
}
