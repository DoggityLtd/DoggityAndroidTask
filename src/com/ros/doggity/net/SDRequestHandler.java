package com.ros.doggity.net;

/**
 * Created by Ros Stepanyak on 6/10/13.
 */
public class SDRequestHandler implements RequestProcessor
{
    SDRequestHandler nextHandler;
    RequestProcessor handler;

    public SDResult processRequest(SDRequest request)
    {
        return null;
    }

    public void processRequestWithHandler (SDRequest request, RequestProcessor handler)
    {
        SDResult result = processRequest(request);

        if (result == null && nextHandler != null)
        {
            request.pushRequestHandler(handler);
            nextHandler.processRequestWithHandler(request, this);
        }
        else
        {
            reportRequest(request, result, handler);
        }
    }

    public void requestFinished (SDRequest request, SDResult result)
    {
        reportRequest(request, result, request.popRequestHandler());
    }

    public  void reportRequest (SDRequest request, SDResult result, RequestProcessor handler)
    {
        if (request.canceled)
        {
            return;
        }

        if (handler == null)
        {
            request.handler.requestFinished(request, result);
        }
        else
        {
            handler.requestFinished(request, result);
        }
    }

    public void cancelAllRequestsWithOwner(Object owner)
    {

    }

    public  void cancelAllRequests ()
    {

    }
}


