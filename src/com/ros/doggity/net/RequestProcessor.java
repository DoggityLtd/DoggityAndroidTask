package com.ros.doggity.net;

/**
 * Created by Ros Stepanyak on 6/11/13.
 */
public interface RequestProcessor {
    public void requestFinished (SDRequest request, SDResult result);
}
