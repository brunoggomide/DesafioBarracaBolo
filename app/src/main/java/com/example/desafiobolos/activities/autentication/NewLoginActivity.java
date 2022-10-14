package com.example.desafiobolos.activities.autentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.adapters.ViewPagerAdapter;
import com.example.desafiobolos.fragment.AdminFragment;
import com.example.desafiobolos.fragment.ClienteFragment;
import com.google.android.material.tabs.TabLayout;

public class NewLoginActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager view_pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        iniciaComponentes ();
        configTabLayout ();
    }

    private void configTabLayout () {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ClienteFragment(), "Cliente");
        viewPagerAdapter.addFragment(new AdminFragment(), "Administrador");

        view_pager.setAdapter(viewPagerAdapter);

        tab_layout.setElevation(0);
        tab_layout.setupWithViewPager(view_pager);

    }

    private void iniciaComponentes () {
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
    }
}