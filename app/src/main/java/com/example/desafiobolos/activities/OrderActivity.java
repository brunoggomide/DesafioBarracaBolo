package com.example.desafiobolos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
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
            case R.id.home:
                Intent intent = new Intent(OrderActivity.this, DeliveryActivity.class);
                startActivity(intent);
                return true;
            case R.id.basket:
                Toast.makeText(this, "Ja está na cesta", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delivery:
                Intent intent2 = new Intent(OrderActivity.this, DeliveryActivity.class);
                startActivity(intent2);
                return true;
            case R.id.profile:
                Intent intent3 = new Intent(OrderActivity.this, ProfileActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}