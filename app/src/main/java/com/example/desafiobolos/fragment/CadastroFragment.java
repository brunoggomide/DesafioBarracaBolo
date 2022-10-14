package com.example.desafiobolos.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.example.desafiobolos.model.Login;
import com.example.desafiobolos.model.User;


public class CadastroFragment extends Fragment {

    private EditText name;
    private EditText data_nasc;
    private EditText cpf;
    private EditText passwd;
    private EditText passwd_confirm;
    private EditText email;
    private EditText contato;
    private Button cadastro_button;





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



                User user = new User();
                user.setCpf(registroGeral);
                user.setSenha(senha);
                user.setEmail(emailPrincipal);
                user.setNome(nomeCompleto);
                user.setTelefone(numeroTelefone);
                user.setDataNasc(datanasc);


                criarConta(user);

            }else {
                passwd.requestFocus();
                passwd.setError("Informe sua senha");

            }

        }else {
            cpf.requestFocus();
            cpf.setError("Informe o seu cpf");

        }

    }

    private void criarConta (User user) {
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                user.getEmail(), user.getSenha()

        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                String id = task.getResult().getUser().getUid();

                user.setId(id);
                user.salvar();

                Login login = new Login(id, "C", false);
                login.salvar();

                //getActivity().finish(); falta implementar

            }else {

                autenticationError(FirebaseHelper.validaErros(task.getException().getMessage()));
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

        name = view.findViewById(R.id.name);
        data_nasc = view.findViewById(R.id.data_nasc);
        cpf = view.findViewById(R.id.cpf);
        passwd = view.findViewById(R.id.passwd);
        passwd_confirm = view.findViewById(R.id.passwd_confirm);
        cadastro_button = view.findViewById(R.id.login_button);
        contato = view.findViewById(R.id.contato);
        email = view.findViewById(R.id.email);

    }
}