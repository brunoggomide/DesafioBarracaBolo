package com.example.desafiobolos.models;

import com.example.desafiobolos.Validators;
import com.example.desafiobolos.helper.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;

public class Login implements Validators {

    private String id;
    private String tipo; // tipo user ou tipo admin
    private boolean acesso;

    public Login() {
    }

    public Login(String id, String tipo, boolean acesso) {
        this.id = id;
        this.tipo = tipo;
        this.acesso = acesso;
    }

    public void salvar (){
        DatabaseReference loginRef = FirebaseHelper.getDatabaseReference().child("login").child(getId());
        loginRef.setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }
}
