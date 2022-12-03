package com.example.desafiobolos.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;
            case R.id.basket:
                Intent intent1 = new Intent(ProfileActivity.this, OrderActivity.class);
                startActivity(intent1);
                return true;
            case R.id.delivery:
                Intent intent2 = new Intent(ProfileActivity.this, DeliveryActivity.class);
                startActivity(intent2);
                return true;
            case R.id.profile:
                Toast.makeText(this, "Ja está em perfil", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}