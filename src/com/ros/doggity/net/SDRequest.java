package com.ros.doggity.net;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.ArrayList;

/**
 * Created by Ros Stepanyak on 6/10/13.
 */
public class SDRequest {
    ArrayList <RequestProcessor> handlerStack;
    boolean canceled;
    public ResponseReciever owner;
    public RequestProcessor handler;

    static public Object requestWithOwner(ResponseReciever owner)
    {
        SDRequest request = new SDRequest();
        request.owner = owner;

        return  request;
    }

    public SDRequest()
    {
        handlerStack = new ArrayList<RequestProcessor>();
    }

    public HttpRequestBase serviceURLRequest()
    {
        return null;
    }

    public SDResult emptyResponse()
    {
        return null;
    }

    public void pushRequestHandler(RequestProcessor handler)
    {
        if (handler != null)
        {
            handlerStack.add(handler);
        }
    }

    public RequestProcessor popRequestHandler()
    {
        if (handlerStack.size() == 0)
        {
            return null;
        }

        return handlerStack.remove(handlerStack.size() - 1);
    }
}