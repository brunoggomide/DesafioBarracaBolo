package com.example.desafiobolos.activities.autentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.example.desafiobolos.model.Cliente;
import com.example.desafiobolos.model.Login;


public class RegistrerActivity extends AppCompatActivity {

    private EditText name;
    private EditText data_nasc;
    private EditText cpf;
    private EditText passwd;
    private EditText passwd_confirm;
    private EditText email;
    private EditText contato;
    private Button cadastro_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        getSupportActionBar().hide();
        iniciaComponentes();
        configClicks();
    }

    private void configClicks(){
        cadastro_button.setOnClickListener(v -> validaDados());

    }

    private void validaDados() {

        String registroGeral = cpf.getText().toString();
        String senha = passwd.getText().toString();
        String confirmaSenha = passwd_confirm.getText().toString();
        String datanasc = data_nasc.getText().toString();
        String emailPrincipal = email.getText().toString();
        String nomeCompleto = name.getText().toString();
        String numeroTelefone = contato.getText().toString();


        if (!registroGeral.isEmpty()) {
            if (!senha.isEmpty()) {

                Cliente cliente = new Cliente();
                cliente.setCpf(registroGeral);
                cliente.setSenha(senha);
                cliente.setEmail(emailPrincipal);
                cliente.setNome(nomeCompleto);
                cliente.setTelefone(numeroTelefone);
                cliente.setDataNasc(datanasc);


                criarConta(cliente);

            }else {
                passwd.requestFocus();
                passwd.setError("Informe sua senha");

            }

        }else {
            cpf.requestFocus();
            cpf.setError("Informe o seu cpf");

        }

    }

    private void criarConta (Cliente cliente) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                cliente.getEmail(), cliente.getSenha()

        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                String id = task.getResult().getUser().getUid();

                cliente.setId(id);
                cliente.salvar();

                Login login = new Login(id, "C", false);
                login.salvar();

                //getActivity().finish(); falta implementar

            }else {

                autenticationError(FirebaseHelper.validaErros(task.getException().getMessage()));
            }
        });

    }

    private void autenticationError(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrerActivity.this);
        builder.setTitle("Alerta");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", ((dialogInterface, i) -> {
            dialogInterface.dismiss();
        }));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void iniciaComponentes(){

        name = findViewById(R.id.name);
        data_nasc = findViewById(R.id.data_nasc);
        cpf = findViewById(R.id.cpf);
        passwd = findViewById(R.id.passwd);
        passwd_confirm = findViewById(R.id.passwd_confirm);
        cadastro_button = findViewById(R.id.cadastro_button);
        contato = findViewById(R.id.contato);
        email = findViewById(R.id.email);

    }

}