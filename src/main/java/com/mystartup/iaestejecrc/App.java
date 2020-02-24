package com.mystartup.iaestejecrc;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ASB4MX1YvFHFf3oenQ4zFPASlkCJ4cxGTbDutyDu")
                // if defined
                .clientKey("NsbRzEWYnfNxjVIoDS0Olc1yFzkCs8PdbT4DIEPZ")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}