package com.laila.unifacs.avaliacao1.activity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laila.unifacs.avaliacao1.R;
import com.laila.unifacs.avaliacao1.activity.data.DAOLocation;

import java.util.List;

public class ListaHistoricoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Location> locationList;
    MyRecyclerViewAdapter myAdapter;
    DAOLocation daoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historico);

        daoLocation = new DAOLocation();

        this.recyclerView = findViewById(R.id.my_recycler_view);

        MyLocation myLocation = (MyLocation) getApplicationContext();
        this.locationList = myLocation.getLocationList();

        getDataFromDatabase();

        myAdapter = new MyRecyclerViewAdapter(this, locationList);
        this.recyclerView.setAdapter(myAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void clearHistory(View view) {

        this.locationList.clear();
        daoLocation.deleteAllLocationLists();
        myAdapter.notifyDataSetChanged();

    }

//    private void addListToDatabase(List<Location> locationList) {
//        DAOLocation daoLocation = new DAOLocation();
//
//        daoLocation.addLocation(locationList, ListaHistoricoActivity.this);
//    }

    private void getDataFromDatabase() {

        if(this.locationList == null) {

            daoLocation = new DAOLocation();
            this.locationList = daoLocation.retrieveLocationList();
        }
    }
}