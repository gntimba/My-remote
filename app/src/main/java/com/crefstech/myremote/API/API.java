package com.crefstech.myremote.API;

import android.content.Context;

import com.crefstech.myremote.R;

public class API {
    public static APIIinterface getAPIService(Context context) {

        return retroClient.getClient(context.getString(R.string.url)).create(APIIinterface.class);
    }
}
