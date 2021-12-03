package com.laila.unifacs.avaliacao1.activity.data;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.laila.unifacs.avaliacao1.R;

import java.util.ArrayList;
import java.util.List;

public class DAOLocation {

    private DatabaseReference databaseReference;
    private List<Location> locationList;
    private List<String> locationKeysString;

    public DAOLocation() {

        // Get reference to Firebase instance
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference().child("LocationList");
        locationKeysString =  new ArrayList<>();
    }

//    public Task<Void> addLocation(List<Location> locationList) {
//
//        return databaseReference.push().setValue(locationList);
//    }

    public Task<Void> addLocation(List<Location> locationList, Context context) {

        getKeysList();
        int itemNumber = locationKeysString.size();
        String keyValue = new String();
        Task<Void> task;

        if (itemNumber == 0) {
            keyValue = "LocationList " + 1;
            locationKeysString.add(keyValue);
            task = databaseReference
                    .child(keyValue)
                    .setValue(locationList)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, R.string.add_to_database_success, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, R.string.add_to_database_failure, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            keyValue = "LocationList " + (itemNumber + 1);
            locationKeysString.add(keyValue);
            task = databaseReference
                    .child(keyValue)
                    .setValue(locationList)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, R.string.add_to_database_success, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, R.string.add_to_database_failure, Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        return task;
    }

    public List<Location> retrieveLocationList() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    int size = (int) snapshot.getChildrenCount();

                    if (size > 1000) {

                        int key = getLastItemId();
                        deleteLocationList(key);
                    }

                    Location location = dataSnapshot.getValue(Location.class);
                    locationList.add(location);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return locationList;
    }

    public void deleteLocationList(int key) {

        String keyString = String.valueOf(key);
        databaseReference.child(keyString).removeValue();

    }

    public void deleteAllLocationLists() {

        databaseReference.removeValue();
    }

    private int getLastItemId() {

        int key = Integer.parseInt(databaseReference.orderByKey().limitToLast(1).getRef().getKey());
        return key;
    }

    private void getKeysList() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren()) {
                    locationKeysString.add(snapshot.getKey());;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
