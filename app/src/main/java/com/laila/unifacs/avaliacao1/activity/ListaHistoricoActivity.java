package com.laila.unifacs.avaliacao1.activity;

import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laila.unifacs.avaliacao1.R;

import java.util.List;

public class ListaHistoricoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historico);

        this.recyclerView = findViewById(R.id.my_recycler_view);

        MyLocation myLocation = (MyLocation) getApplicationContext();
        this.locationList = myLocation.getLocationList();

        MyRecyclerViewAdapter myAdapter = new MyRecyclerViewAdapter(this, locationList);
        this.recyclerView.setAdapter(myAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}