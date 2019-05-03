package com.example.hamz.dedymizwarapp.helper;

import im.crisp.sdk.Crisp;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Crisp.initialize(this);
        Crisp.getInstance().setWebsiteId("0f85b388-6138-4734-98bb-d3e88bb74ad6");
    }
}
