package com.laila.unifacs.avaliacao1.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.laila.unifacs.avaliacao1.R;
import com.laila.unifacs.avaliacao1.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private SharedPreferences sharedPreferences;

    Location currentLocation;

    Marker currentLocationMarker;

    // Google's API for location services
    private FusedLocationProviderClient fusedLocationProviderClient;

    // Location request is a config file for all settings related to FusedLocationProviderClient
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private TextView latitudeTextView, longitudeTextView, speedTextView;

    private int orientacaoMapaRadioButtonSelected;
    private int tipoMapaRadioButtonSelected;
    private int infoTrafegoRadioButtonSelected;
    private int coordGeograficasRadionButtonSelected;
    private int unidadeVelocidadeRadioButtonSelected;

    private final String permission = Manifest.permission.ACCESS_FINE_LOCATION;

    private final String PREFERENCE_NAME = "myPref";
    private final String ORIENTACAO_MAPA_KEY = "orientacaoMapaKey";
    private final String TIPO_MAPA_KEY = "tipoMapaKey";
    private final String INFO_TRAFEGO_KEY = "infoTrafegoKey";
//    private final String VELOCIDADE_KEY = "velocidadeKey";
//    private final String LATITUDE_KEY = "latitudeKey";
//    private final String LONGITUDE_KEY = "longitudeKey";
    private final String COORD_GEO_KEY = "coordGeoKey";
    private final String UNIDADE_VELOCIDADE_KEY = "unidadeVelocidadeKey";

    public static final int PERMISSION_FINE_LOCATION = 99;
    public static final int DEFAULT_UPDATE_INTERVAL = 1;
    public static final int FAST_UPDATE_INTERVAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.setViews();
        this.setLocationRequestConfiguration();
        this.setLocationCallbackConfiguration();
        this.setFusedLocationProviderClient();
        this.getPreferences();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.setMapConfigurations();

        this.askForPermission();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // TODO Implement here the logic for when permission is granted

                } else {
                    Toast.makeText(MapsActivity.this, R.string.app_requires_permission, Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void setLocationRequestConfiguration() {
        this.locationRequest = LocationRequest.create()
                .setInterval(1000 * DEFAULT_UPDATE_INTERVAL)
                .setFastestInterval(1000 * FAST_UPDATE_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    // Event that is triggered every time the update interval is met
    // Evento que é chamado toda vez que o intervalo de tempo é atingido
    private void setLocationCallbackConfiguration() {

        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                // Gets last known location
                // Recebe o valor da última localização
                MapsActivity.this.currentLocation =locationResult.getLastLocation();

                // If there's already a marker, remove it
                if (currentLocationMarker != null) {
                    currentLocationMarker.remove();
                }

                // Update status bar
                // Atualiza a barra de status
                updateUIViews(currentLocation);

                // Place marker on map
                // Coloca o marcador no mapa
                placeMarker(currentLocation);
            }
        };
    }

    private void askForPermission() {

        if(ActivityCompat.checkSelfPermission(MapsActivity.this, this.permission) == PackageManager.PERMISSION_GRANTED) {

            startLocationUpdates();

        } else {

            // Permission not granted
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{this.permission}, PERMISSION_FINE_LOCATION);
            }
        }

    }

    private void setFusedLocationProviderClient() {
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsActivity.this);
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        this.fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        this.fusedLocationProviderClient.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location != null) {
                    currentLocation = location;
                }
            }
        });
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(this.locationCallback);
    }

    private void getPreferences() {

        // Recupera o objeto sharedPreferences que contém a tabela com preferências salvas através do nome da preferência
        this.sharedPreferences = getSharedPreferences(this.PREFERENCE_NAME, 0);

        // Recupera o id do radioButton selecionado de cada RadioGroup
        this.orientacaoMapaRadioButtonSelected = this.sharedPreferences.getInt(this.ORIENTACAO_MAPA_KEY, 0);
        this.tipoMapaRadioButtonSelected = this.sharedPreferences.getInt(this.TIPO_MAPA_KEY, 0);
        this.infoTrafegoRadioButtonSelected = this.sharedPreferences.getInt(this.INFO_TRAFEGO_KEY, 0);
        this.coordGeograficasRadionButtonSelected = this.sharedPreferences.getInt(this.COORD_GEO_KEY, 0);
        this.unidadeVelocidadeRadioButtonSelected = this.sharedPreferences.getInt(this.UNIDADE_VELOCIDADE_KEY, 0);
    }

    private void setViews() {

        this.latitudeTextView = findViewById(R.id.latitude_value_textView);
        this.longitudeTextView = findViewById(R.id.longitude_value_textView);
        this.speedTextView = findViewById(R.id.speed_value_textView);

    }

    private void updateUIViews(Location location) {

        this.latitudeTextView.setText(String.valueOf(location.getLatitude()));
        this.longitudeTextView.setText(String.valueOf(location.getLongitude()));

        if (location.hasSpeed()) {

            this.speedTextView.setText(String.valueOf(location.getSpeed()));

        } else {

            this.speedTextView.setText(R.string.velocicade_nao_disponivel);

        }
    }

    // Place marker on map
    // Coloca o marcador no mapa
    private void placeMarker(Location location){

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.top_view_car_40);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Marker");
        markerOptions.icon(icon);
        markerOptions.position(latLng);

        currentLocationMarker = mMap.addMarker(markerOptions);
        currentLocationMarker.setRotation(location.getBearing());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void setMapConfigurations() {

        this.changeMapType();
        this.setTrafficInfo();
    }

    private void changeMapType() {

        // Checks which radioButton is selected
        // Checa qual radioButton está selecionado
        if(tipoMapaRadioButtonSelected == R.id.vetorial_RadioButton) {

            // Vector map type is selected
            // Mapa do tipo Vector está selecionado
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }
        else if(tipoMapaRadioButtonSelected == R.id.imagem_satelite_RadioButton) {

            // Satellite map type selected
            // Mapa do tipo Satélite está selecionado
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

    }

    private void setTrafficInfo() {

        // Checks if traffic info is enabled
        // Checa se o informação de tráfico está ligada
        if (infoTrafegoRadioButtonSelected == R.id.trafego_ligado_RadioButton) {

            // Traffic info is enabled
            // Informação de tráfico está ligada
            mMap.setTrafficEnabled(true);
        }
        else if (infoTrafegoRadioButtonSelected == R.id.trafego_desligado_RadioButton) {

            // Traffic info is disabled
            // Informação de tráfico está desligada
            mMap.setTrafficEnabled(false);
        }
    }

}