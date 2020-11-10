package samir.alakbarov.crbn.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import samir.alakbarov.crbn.R;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    
    private Button onlyRailBT;
    private boolean isOnlyRail = true;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        
        onlyRailBT = findViewById(R.id.onlyRailBT);
        
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        
    }
    
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng baku = new LatLng(40.380725, 49.847815);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baku, 13));
        
        // loading custom map style
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.only_rail_style);
        
        
        onlyRailBT.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // changing between default and custom styles
                if (isOnlyRail) {
                    googleMap.setMapStyle(style);
                    onlyRailBT.setText("Default map!");
                } else {
                    googleMap.setMapStyle(null);
                    onlyRailBT.setText("Only rail!");
                }
                isOnlyRail = !isOnlyRail;
            }
        });
        
        
    }
    
    
}