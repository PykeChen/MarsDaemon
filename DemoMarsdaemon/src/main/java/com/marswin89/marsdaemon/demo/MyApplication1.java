package com.marswin89.marsdaemon.demo;

import android.content.Context;
import android.util.Log;

import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;

/**
 * Implementation 1<br/>
 * override one method is ok.<br/>
 *
 * Created by Mars on 12/24/15.
 */
public class MyApplication1 extends DaemonApplication {

    private static final String TAG = "cpy";

    /**
     * you can override this method instead of {@link android.app.Application attachBaseContext}
     * @param base
     */
    @Override
    public void attachBaseContextByDaemon(Context base) {
        super.attachBaseContextByDaemon(base);
    }


    /**
     * give the configuration to lib in this callback
     * @return
     */
    @Override
    protected DaemonConfigurations getDaemonConfigurations() {
        DaemonConfigurations.DaemonConfiguration configuration1 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:process1",
                Service1.class.getCanonicalName(),
                Receiver1.class.getCanonicalName());

        DaemonConfigurations.DaemonConfiguration configuration2 = new DaemonConfigurations.DaemonConfiguration(
                "com.marswin89.marsdaemon.demo:process2",
                Service2.class.getCanonicalName(),
                Receiver2.class.getCanonicalName());

        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        //return new DaemonConfigurations(configuration1, configuration2);//listener can be null
        return new DaemonConfigurations(configuration1, configuration2, listener);
    }


    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
            Log.d(TAG, "onPersistentStart() called with: context = [" + context + "]");
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
            Log.d(TAG, "onDaemonAssistantStart() called with: context = [" + context + "]");
        }

        @Override
        public void onWatchDaemonDaed() {
            Log.d(TAG, "onWatchDaemonDaed() called");
        }
    }
}
