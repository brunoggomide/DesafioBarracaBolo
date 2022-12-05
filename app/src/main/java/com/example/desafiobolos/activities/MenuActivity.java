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

import com.example.desafiobolos.adapters.CardapioAdapter;
import com.example.desafiobolos.db.CardapioDB;

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
        layoutManager = new LinearLayoutManager(this);
        adapter = new CardapioAdapter(this, new CardapioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MenuActivity.this, ShowItemActivity.class);
                intent.putExtra(EXTRA_SHOW, CardapioDB.myMenu.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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