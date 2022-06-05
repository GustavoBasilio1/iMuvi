package com.example.imuvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    ImageButton button_Search;
    ImageButton button_Map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        button_Search = findViewById(R.id.btn_search);
        button_Map = findViewById(R.id.btn_map);

        button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent user = new Intent(getApplicationContext(), Search.class);
                startActivity(user);
            }
        });

        button_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent user = new Intent(getApplicationContext(), EnderecoActivity.class);
                startActivity(user);
            }
        });
    }
}