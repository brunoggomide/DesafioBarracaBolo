package com.example.desafiobolos.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.desafiobolos.activities.R;

import com.example.desafiobolos.activities.autentication.RegistrerActivity;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.example.desafiobolos.model.Login;
import com.example.desafiobolos.usuario.MenuActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class ClienteFragment extends Fragment {


    private EditText cpf;
    private EditText passwd;
    private ProgressBar progress_bar;
    private Button login_button;
    private TextView cadastro;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        iniciaComponentes (view);

        configClicks();


        return view;
    }

    private void configClicks(){



        cadastro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrerActivity.class);
                getContext().startActivity(intent);
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

        String registroGeral = cpf.getText().toString();
        String senha = passwd.getText().toString();

        if (!registroGeral.isEmpty()){
            if (!senha.isEmpty()){

                progress_bar.setVisibility(View.VISIBLE);
                logar(registroGeral, senha);

            }else {
                passwd.requestFocus();
                passwd.setError("senha invalida");

            }

        }else {
            cpf.requestFocus();
            cpf.setError("Cpf invalido");

        }

    }

    private void logar (String registroGeral, String senha) {



        FirebaseHelper.getAuth().signInWithEmailAndPassword(registroGeral, senha).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                verificaCadastro(task.getResult().getUser().getUid());

            }else {
                progress_bar.setVisibility(View.GONE);
                autenticationError(FirebaseHelper.validaErros(task.getException().getMessage()));

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
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    startActivity(intent);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void autenticationError(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Alerta");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", ((dialogInterface, i) -> {
            dialogInterface.dismiss();
        }));

        AlertDialog dialog = builder.create();
        dialog.show();

    }



    private void iniciaComponentes(View view){

        cpf = view.findViewById(R.id.cpf);
        passwd = view.findViewById(R.id.passwd);
        progress_bar = view.findViewById(R.id.progress_bar);
        login_button = view.findViewById(R.id.login_button);
        cadastro = view.findViewById(R.id.cadastro);


    }
}