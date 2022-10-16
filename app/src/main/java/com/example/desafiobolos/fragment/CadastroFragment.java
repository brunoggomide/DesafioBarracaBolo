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
import com.example.desafiobolos.model.Cliente;
import com.example.desafiobolos.model.Login;



public class CadastroFragment extends Fragment implements View.OnClickListener {

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
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        iniciaComponentes (view);
        onClick(view);



        return view;
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
                if(!datanasc.isEmpty()){
                    if(!nomeCompleto.isEmpty()){
                        if(!emailPrincipal.isEmpty()){
                            if (!numeroTelefone.isEmpty()){

                                Cliente cliente = new Cliente();
                                cliente.setCpf(registroGeral);
                                cliente.setSenha(senha);
                                cliente.setEmail(emailPrincipal);
                                cliente.setNome(nomeCompleto);
                                cliente.setTelefone(numeroTelefone);
                                cliente.setDataNasc(datanasc);
                                cliente.setConfirmaSenha(confirmaSenha);


                                criarConta(cliente);

                            }else {
                                contato.requestFocus();
                                contato.setError("Por favor digite um numero de telefone");
                            }

                        }else {
                            email.requestFocus();
                            email.setError("Digite um e-mail valido");
                        }

                    }else {
                        name.requestFocus();
                        name.setError("Digite o seu nome");
                    }

                }else {
                    data_nasc.requestFocus();
                    data_nasc.setError("Informe sua data de nascimento");

                }

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
        cadastro_button = view.findViewById(R.id.cadastro_button);
        contato = view.findViewById(R.id.contato);
        email = view.findViewById(R.id.email);

    }

    @Override
    public void onClick(View view) {
        cadastro_button.setOnClickListener(v -> validaDados());


    }
}