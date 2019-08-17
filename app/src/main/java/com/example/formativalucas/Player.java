package com.example.formativalucas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Player extends AppCompatActivity {

    TextView edNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //declarado intent para pegar dados passador por outras telas
        Intent it = getIntent();
        //instanciando objetos
        edNome = findViewById(R.id.edNameUser);

        edNome.setText(it.getStringExtra("nomeUser"));
    }
}
