package com.example.smartirrigation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView rainSensorTextView;
    private TextView soilMoistureTextView;
    private TextView waterPumpStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        rainSensorTextView = findViewById(R.id.rainSensorTextView);
        soilMoistureTextView = findViewById(R.id.soilMoistureTextView);
        waterPumpStatusTextView = findViewById(R.id.waterPumpStatusTextView);

        // Retrieve data from Firebase Realtime Database
        FirebaseDatabase.getInstance().getReference("Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Rain Sensor").exists()) {
                    String rainSensorData = dataSnapshot.child("Rain Sensor").getValue().toString();
                    rainSensorTextView.setText(rainSensorData);
                }

                if (dataSnapshot.child("Soil Moisture").exists()) {
                    String soilMoistureData = dataSnapshot.child("Soil Moisture").getValue().toString();
                    soilMoistureTextView.setText(soilMoistureData);
                }

                if (dataSnapshot.child("Water Pump Status").exists()) {
                    String waterPumpStatusData = dataSnapshot.child("Water Pump Status").getValue().toString();
                    waterPumpStatusTextView.setText(waterPumpStatusData);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}
