package com.example.imuvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    ImageButton button_voltar;
    ImageButton button_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        button_entrar = (ImageButton) findViewById(R.id.button_entrar);
        button_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(getApplicationContext(), Adm.class);
                startActivity(user);
            }
        });


        button_voltar = (ImageButton) findViewById(R.id.button_voltar);
        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(user);
            }
        });




    }
}
