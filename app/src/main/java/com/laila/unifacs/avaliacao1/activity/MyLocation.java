package com.laila.unifacs.avaliacao1.activity;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class MyLocation extends Application {

    // Single instance of the class
    // Única instância da classe
    private static MyLocation instance;

    private List<Location> locationList;

    public MyLocation getInstance() {return instance;}

    public void onCreate() {
        super.onCreate();
        instance = this;
        locationList = new ArrayList<>();
    }

    // region Getters and Setters

    public List<Location> getLocationList() {return this.locationList;}

    public void setLocationList(List<Location> locationList) {this.locationList = locationList;}

    // endregion
}
