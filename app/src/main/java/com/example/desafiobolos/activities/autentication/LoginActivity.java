package com.example.desafiobolos.activities.autentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.example.desafiobolos.models.Login;
import com.example.desafiobolos.usuario.MenuActivity;
import com.example.desafiobolos.activities.autentication.RegistrerActivity;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.example.desafiobolos.models.Login;
import com.example.desafiobolos.usuario.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView cadastro;
    private Button login_button;
    private EditText passwd;
    private EditText email;
    private ProgressBar progress_bar;
    private DatabaseReference databaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IniciarComponentes();
        configClicks();


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

    private void logar (String mail, String senha) {


        FirebaseHelper.getAuth().signInWithEmailAndPassword(mail, senha).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                verificaCadastro(task.getResult().getUser().getUid());

            }else {
                progress_bar.setVisibility(View.GONE);
//                autenticationError(FirebaseHelper.validaErros(task.getException().getMessage()));


            }
        });

    }

    private void verificaCadastro(String idCliente){
        DatabaseReference loginRef = FirebaseHelper.getDatabaseReference().child("login").child(idCliente);
        loginRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Login login = snapshot.getValue(Login.class);

                if(login.isAcesso()){
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    private void autenticationError(String msg){
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//        builder.setTitle("Alerta");
//        builder.setMessage(msg);
//        builder.setPositiveButton("OK", ((dialogInterface, i) -> {
//            dialogInterface.dismiss();
//        }));
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//    }



    private void IniciarComponentes() {

        cadastro = findViewById(R.id.cadastro);
        login_button = findViewById(R.id.login_button);
        passwd = findViewById(R.id.passwd);
        email = findViewById(R.id.email);
        progress_bar = findViewById(R.id.progress_bar);
    }
}