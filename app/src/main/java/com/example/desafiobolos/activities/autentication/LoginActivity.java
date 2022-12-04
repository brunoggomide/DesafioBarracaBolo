package com.example.desafiobolos.activities.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.desafiobolos.activities.MenuActivity;
import com.example.desafiobolos.activities.R;

public class LoginActivity extends AppCompatActivity {

    private TextView cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        IniciarComponentes();

        EditText email = findViewById(R.id.email);
        EditText senha = findViewById(R.id.passwd);

        cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrerActivity.class);
                startActivity(intent);
            }
        });

        AppCompatButton button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textEmail = email.getText().toString();
                String textSenha = senha.getText().toString();

                if (textEmail == "cliente@user.com" && textSenha == "user123") {
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);

                    //Toast.makeText(LoginActivity.this, "Login não autorizado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void IniciarComponentes() {

        cadastro = findViewById(R.id.cadastro);
    }
}