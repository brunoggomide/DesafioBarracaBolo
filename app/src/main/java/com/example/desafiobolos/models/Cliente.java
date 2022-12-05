package com.example.desafiobolos.models;

import com.example.desafiobolos.helper.FirebaseHelper;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

public class Cliente extends User {


    public Cliente() {
        super();
    }

    public Cliente(String id, String nome, String email, String telefone, String senha, String cpf, String dataNasc, String confirmaSenha) {
        super(id, nome, email, telefone, senha, cpf, dataNasc, confirmaSenha);
    }

    public void salvar (){

        DatabaseReference userRef = FirebaseHelper.getDatabaseReference().child("usuarios").child(getId());
        userRef.setValue(this);

        FirebaseUser user = FirebaseHelper.getAuth().getCurrentUser();

        UserProfileChangeRequest profileUser = new UserProfileChangeRequest.Builder().setDisplayName(getNome()).build();

        if (user != null) user.updateProfile(profileUser);

    }
}
