package com.example.desafiobolos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.desafiobolos.adapters.PedidosAdapter;
import com.example.desafiobolos.models.Pedidos;

public class DeliveryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView= findViewById(R.id.menu_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PedidosAdapter(this, new PedidosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

}