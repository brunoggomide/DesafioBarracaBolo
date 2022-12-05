package com.example.desafiobolos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {


    private EditText endereco, bairro, cmp, cidade, loc;
    private View card;
    private SwitchCompat switchDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        switchDelivery = findViewById(R.id.switchEntrega);
        endereco = findViewById(R.id.endereco);
        bairro = findViewById(R.id.bairro);
        cmp = findViewById(R.id.cmp);
        cidade = findViewById(R.id.cidade);
        loc = findViewById(R.id.loc);
        card = findViewById(R.id.containerComponents);

        switchDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    card.setVisibility(View.GONE);
                    endereco.setVisibility(View.GONE);
                    bairro.setVisibility(View.GONE);
                    cmp.setVisibility(View.GONE);
                    cidade.setVisibility(View.GONE);
                    loc.setVisibility(View.GONE);
                } else {
                    card.setVisibility(View.VISIBLE);
                    endereco.setVisibility(View.VISIBLE);
                    bairro.setVisibility(View.VISIBLE);
                    cmp.setVisibility(View.VISIBLE);
                    cidade.setVisibility(View.VISIBLE);
                    loc.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}