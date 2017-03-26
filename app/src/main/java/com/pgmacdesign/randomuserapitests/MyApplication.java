package com.pgmacdesign.randomuserapitests;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.pgmacdesign.pgmacutilities.utilities.DatabaseUtilities;

/**
 * Normal centralized app class. Used for grabbing context when needed amongst other
 * small purposes.
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication sInstance;
    private static Context context;
    private static DatabaseUtilities dbUtils;

    public MyApplication(){
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = getApplicationContext();
        getDatabase();
    }

    public static synchronized MyApplication getInstance(){
        return sInstance;
    }

    public static synchronized Context getContext(){
        return context;
    }

    /**
     * My DB wrapper getter
     * @return {@link DatabaseUtilities}
     */
    public static synchronized DatabaseUtilities getDatabase(){
        if(dbUtils == null){
            dbUtils = new DatabaseUtilities(getContext());
        }
        return dbUtils;
    }
}
