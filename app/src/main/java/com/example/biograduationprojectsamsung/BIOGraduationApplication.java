package com.example.biograduationprojectsamsung;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.sync.SyncConfiguration;

public class BIOGraduationApplication extends Application {
    private static BIOGraduationApplication mAppInstance=null;
    public static Context appContext;

    public App app = new App(new AppConfiguration.Builder("biograduationproject-aopdz")
                .build());

    public static BIOGraduationApplication getInstance() {
        return mAppInstance;
    }
    public static BIOGraduationApplication get() {
        return get(appContext);
    }

    public static BIOGraduationApplication get(Context context) {
        return (BIOGraduationApplication) context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance=this;
        appContext=getApplicationContext();

        Realm.init(com.example.biograduationprojectsamsung.BIOGraduationApplication.getInstance().getApplicationContext());



    }



}
