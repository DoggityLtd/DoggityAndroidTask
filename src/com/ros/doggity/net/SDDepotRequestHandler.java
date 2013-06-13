package com.ros.doggity.net;

import java.util.ArrayList;

/**
 * Created by Ros Stepanyak on 6/12/13.
 */
public class SDDepotRequestHandler extends SDRequestHandler implements RequestProcessor {
    private ArrayList<SDAsyncRequest> currentRequests;

    public SDDepotRequestHandler() {
        currentRequests = new ArrayList<SDAsyncRequest>();
    }


    public void cancelAllRequests()
    {

    }

    public void cancelAllRequestsWithOwner(Object owner)
    {
        for (SDAsyncRequest request : currentRequests)
        {
            if (request.owner.equals(owner))
            {
               // request.cancel();
            }
        }
    }
@Override
    public void processRequestWithHandler (SDRequest request, RequestProcessor handler)
    {
        SDAsyncRequest asyncRequest = new SDAsyncRequest();
        asyncRequest.setOwner(request.owner);

        currentRequests.add(asyncRequest);
        asyncRequest.execute(request);
    }

    public void requestFinished(SDRequest request, SDResult reponse) {
        currentRequests.remove(request);
    }
}
