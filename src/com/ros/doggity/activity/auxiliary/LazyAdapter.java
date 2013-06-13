package com.ros.doggity.activity.auxiliary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ros.doggity.model.Venue;
import com.ros.doggity.R;

import java.util.ArrayList;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    ArrayList <Venue> venues;
    
    /* fetched arraylist of venues as data source for table view*/
    public LazyAdapter(Activity a, ArrayList<Venue> venues) {
        activity = a;
        this.venues = venues;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return venues.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);

        //name of the venue
        TextView nameTextView = (TextView)vi.findViewById(R.id.text);
        ImageView image = (ImageView)vi.findViewById(R.id.image);
        nameTextView.setText(venues.get(position).getName());

        // venue address
        TextView addressTextView = (TextView)vi.findViewById(R.id.textAddress);
        addressTextView.setText(venues.get(position).getAddress());

        // venue max dog size
        TextView maxDogSizeTextView = (TextView)vi.findViewById(R.id.textDogSize);
        maxDogSizeTextView.setText(venues.get(position).getMaxDogSize() + " size allowed");


        String picURL = venues.get(position).getPictureURLForDisplay();
        if(picURL != null) {
            picURL = new StringBuilder().append("http://res.dev.friendbuy.de/").append(picURL).toString();
        }
        else{

            picURL =  "http://www.meritsolutions.com.au/wp-content/uploads/2011/10/QuestionMark_Job_Interviews-200x200.jpg";

        }
        imageLoader.DisplayImage(picURL, image);
        return vi;
    }
}