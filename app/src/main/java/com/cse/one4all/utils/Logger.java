package com.cse.one4all.utils;

import com.cse.one4all.managers.ResourcesManager;

import org.andengine.util.debug.Debug;

/**
 * Created by chewb on 11/30/2015.
 */
public class Logger {

    public static void debug(final String message){
        ResourcesManager.getInstance().activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Debug.d(message);
            }
        });
    }
}
