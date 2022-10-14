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


public class ClienteFragment extends Fragment {


    private EditText cpf;
    private EditText passwd;
    private ProgressBar progress_bar;
    private Button login_button;




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
        login_button.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {

        String registroGeral = cpf.getText().toString();
        String senha = passwd.getText().toString();

        if (!registroGeral.isEmpty()) {
            if (!senha.isEmpty()) {

                progress_bar.setVisibility(View.VISIBLE);

                User user = new User();
                user.setCpf(registroGeral);
                user.setSenha(senha);

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
                progress_bar.setVisibility(View.GONE);
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

        cpf = view.findViewById(R.id.cpf);
        passwd = view.findViewById(R.id.passwd);
        progress_bar = view.findViewById(R.id.progress_bar);
        login_button = view.findViewById(R.id.login_button);

    }
}