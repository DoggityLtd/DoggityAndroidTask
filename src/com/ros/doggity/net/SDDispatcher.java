package com.ros.doggity.net;

/**
 * Created by Ros Stepanyak on 6/11/13.
 */
public class SDDispatcher {
    private static SDDispatcher instance;
    private SDRequestHandler headRequestHandler;

    public static SDDispatcher sharedInstance() {
        if(instance == null) {
            instance = new SDDispatcher();
            instance.headRequestHandler = new SDDepotRequestHandler();
        }
        return instance;
    }

//    private SDDispatcher() {
//        headRequestHandler = new SDDepotRequestHandler();
//    }

    public void processRequest(SDRequest request)
    {
        headRequestHandler.processRequestWithHandler(request, null);
    }

    public void cancelAllRequestsWithOwner(Object owner)
    {
        headRequestHandler.cancelAllRequestsWithOwner(owner);
    }
}
