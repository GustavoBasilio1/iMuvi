package com.example.imuvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    ImageButton button_Search;
    ImageButton button_Map;
    ImageButton button_admin;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int mov = 0;
    Vibrator vibrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        //adicionando o sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null)
            finish();

        vibrar = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorevent) {
                float x = sensorevent.values[0];
                float y = sensorevent.values[1];
                float z = sensorevent.values[2];
                System.out.println("Valor GiroX" + x);
                if (x < -5 && mov == 0) {
                    vibrar.vibrate(100);
                    Toast.makeText(Main2Activity.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov++;
                } else if (x > -5 && mov == 1) {
                    vibrar.vibrate(100);
                    Toast.makeText(Main2Activity.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov++;

                }

                if (mov == 2) {
                    vibrar.vibrate(100);
                    Toast.makeText(Main2Activity.this, "This app does not support landscape mode", Toast.LENGTH_SHORT).show();
                    mov = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    Start();

    FindComponents();

        button_admin = (ImageButton) findViewById(R.id.button_admin);
        button_admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent user = new Intent(getApplicationContext(), Login.class);
            startActivity(user);
        }
    });


        button_Search = (ImageButton) findViewById(R.id.btn_search);
        button_Search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent user = new Intent(getApplicationContext(), Search.class);
            startActivity(user);
        }
    });

        button_Map = (ImageButton) findViewById(R.id.btn_map);
        button_Map.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent user = new Intent(getApplicationContext(), EnderecoActivity.class);
            startActivity(user);
        }
    });
}
    //MÉTODOS DO ACELEROMETRO
    private void Start() {
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void Stop() { sensorManager.unregisterListener(sensorEventListener); }

    @Override
    protected void onPause() {
        super.onPause();
        Stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Start();
    }

    private void FindComponents(){
        // button_Search = findViewById(R.id.btn_search);
        // = findViewById(R.id.btn_map);
    }
}