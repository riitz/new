package com.example.mobilemanagementsystem.Sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilemanagementsystem.R;

public class AccelerometerSensorActivity extends AppCompatActivity {

    private TextView showposition;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);

        showposition = findViewById(R.id.txtpos);

        sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float [] values = event.values;
                String xAis ="x: " +values[0];
                String yAxis = "y: " +values[1];
                String zAxis ="z: "  +values[2];

                showposition.setText(xAis + " " +yAxis + "" +zAxis +"");



            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor !=null){
            sensorManager.registerListener(sel,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this,"No sensor found",Toast.LENGTH_SHORT).show();
        }


    }
}
