package com.example.faith.juakali2;

import android.app.IntentService;
import android.content.Intent;
import android.location.Geocoder;
import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * Created by Faith on 2/22/2018.
 */

public class FetchAddressIntentService extends IntentService {

    private static final String TAG = "FetchAddressIS";

    public FetchAddressIntentService(){
        super(TAG);
    }

    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

    }
}
