package com.example.desafiobolos.activities.autentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desafiobolos.activities.MenuActivity;
import com.example.desafiobolos.activities.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView cadastro;
    private Button login_button;
    private EditText passwd;
    private EditText email;
    private ProgressBar progress_bar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cadastro = findViewById(R.id.cadastro);
        login_button = findViewById(R.id.login_button);
        passwd = findViewById(R.id.passwd);
        email = findViewById(R.id.email);
        progress_bar = findViewById(R.id.progress_bar);

        hideProgressBar();
        configClicks();

        mAuth = FirebaseAuth.getInstance();
    }


    private void logar (String mail, String senha) {

        showProgressBar();
        mAuth.signInWithEmailAndPassword(mail, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            updateUI(currentUser);
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Falha no login, verifique seus dados!", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressBar();
                    }
                });

    }

    private void configClicks(){
        cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrerActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaDados(view);
            }
        });

    }

    public void validaDados (View view) {

        String mail = email.getText().toString();
        String senha = passwd.getText().toString();

        if (!mail.isEmpty()){
            if (!senha.isEmpty()){


                logar(mail, senha);

            }else {
                passwd.requestFocus();
                passwd.setError("senha invalida");

            }

        }else {
            email.requestFocus();
            email.setError("E-mail Invalido");

        }

    }

    private void showProgressBar()
    {
        if(progress_bar != null)
            progress_bar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        if(progress_bar != null)
            progress_bar.setVisibility(View.INVISIBLE);
    }

    private void updateUI(FirebaseUser currentUser)
    {
        String title = "Usuário não logado";
        String msg = "Faça login";

        if(currentUser != null)
        {
            title = "Usuário logado";
            msg = currentUser.getEmail();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.create().show();
    }
}