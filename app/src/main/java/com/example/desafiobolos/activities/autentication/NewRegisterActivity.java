package com.example.desafiobolos.activities.autentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.adapters.ViewPagerAdapter;
import com.example.desafiobolos.fragment.AdminFragment;
import com.example.desafiobolos.fragment.CadastroFragment;
import com.example.desafiobolos.fragment.ClienteFragment;

public class NewRegisterActivity extends AppCompatActivity {

    private ViewPager fragmentContainerView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);




    }

}