package com.ros.doggity.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Ros Stepanyak on 6/12/13.
 */
// Uses AsyncTask to create a task away from the main UI thread. This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class SDAsyncRequest extends AsyncTask<SDRequest, Void, String> {

    public Object owner;
    private SDRequest request;

    public void setOwner (Object owner)
    {
        this.owner = owner;
    }


        @Override
        protected String doInBackground(SDRequest... requests) {

            // params comes from the execute() call: params[0] is the url.
            try {
                int count = requests.length;
               // Log.d("sheet", String.valueOf(count));
                this.request = requests[0];
                HttpRequestBase requestBase = this.request.serviceURLRequest();
                HttpClient httpClient =  HttpClientFactory.getThreadSafeClient();
                HttpResponse response = httpClient.execute(requestBase);
                HttpEntity entity = response.getEntity();

                if (entity != null)
                {
                   // InputStream instream = entity.getContent();
                   // String stringResponse = convertStreamToString(instream);
                    String stringResponse = EntityUtils.toString(entity);
                    Log.d("--------------", "Converting");
                    return stringResponse;
                }

                return null;
            } catch (IOException e) {
                Log.d("--------------", "Stack Trace");
                e.printStackTrace();
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Log.d("sheet", result);

            SDResult response = request.emptyResponse();
            response.parseResponseData(result);
            request.owner.handleResponseData(response);
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

    /*
    public void cancel ()
    {
        //TODO: cancel this shit
    }*/
}
