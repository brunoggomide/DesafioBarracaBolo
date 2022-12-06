package com.example.desafiobolos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiobolos.DAO.JSONCardapio;
import com.example.desafiobolos.adapters.CardapioAdapter;
import com.example.desafiobolos.models.Cardapio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_SHOW = "EXTRA_SHOW";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.menu_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-desafio-quinta-etapa.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONCardapio jsonCardapio = retrofit.create(JSONCardapio.class);
        Call<List<Cardapio>> call = jsonCardapio.getCardapio();
        call.enqueue(new Callback<List<Cardapio>>() {
            @Override
            public void onResponse(Call<List<Cardapio>> call, Response<List<Cardapio>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, response.code(), Toast.LENGTH_LONG);
                    return;
                }
                List<Cardapio> cardapioList = response.body();
                CardapioAdapter cardapioAdapter = new CardapioAdapter(MenuActivity.this, cardapioList);
                recyclerView.setAdapter(cardapioAdapter);
            }

            @Override
            public void onFailure(Call<List<Cardapio>> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.basket:
                Intent intent1 = new Intent(MenuActivity.this, OrderActivity.class);
                startActivity(intent1);
                return true;
            case R.id.delivery:
                Intent intent2 = new Intent(MenuActivity.this, DeliveryActivity.class);
                startActivity(intent2);
                return true;
            case R.id.profile:
                Intent intent3 = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}