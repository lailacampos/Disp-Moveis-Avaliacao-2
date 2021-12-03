package com.laila.unifacs.avaliacao1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.laila.unifacs.avaliacao1.R;
import com.laila.unifacs.avaliacao1.activity.data.DAOLocation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    List<Location> locationList;

    private final String PREFERENCE_NAME = "myPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();

        MyLocation myLocation = (MyLocation) getApplicationContext();
        locationList = myLocation.getLocationList();

        DAOLocation daoLocation = new DAOLocation();
        daoLocation.addLocation(locationList, this);
    }

    public void featureNotImplemented(View view) {
        Toast.makeText(this, R.string.feature_not_implemented_toast, Toast.LENGTH_SHORT).show();
    }

    public void changeToConfigScreen(View view) {
        Intent intent = new Intent(this, ConfigScreen.class);
        startActivity(intent);
    }

    public void changeToCreditScreen (View view) {
        Intent intentCredit = new Intent(this, CreditScreen.class);
        startActivity(intentCredit);
    }

    public void changeToNagegationScreen(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void changeToHistoricScreen(View view) {
        Intent intent = new Intent(this, ListaHistoricoActivity.class);
        startActivity(intent);
    }

}