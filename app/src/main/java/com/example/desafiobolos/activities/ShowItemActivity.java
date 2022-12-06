package com.example.desafiobolos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desafiobolos.models.Cardapio;

public class ShowItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);

        TextView nameTextView = findViewById(R.id.nome_textView);
        TextView descTextView = findViewById(R.id.desc_textView);
        TextView vlrTextView = findViewById(R.id.price_textView);

        Intent intent = getIntent();
        Cardapio cardapio = (Cardapio) intent.getSerializableExtra(MenuActivity.EXTRA_SHOW);

        nameTextView.setText(cardapio.getNome());
        descTextView.setText(cardapio.getDescription());
        vlrTextView.setText(cardapio.getValor());
    }
}