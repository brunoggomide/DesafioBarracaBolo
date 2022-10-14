package com.example.desafiobolos.model;

import com.example.desafiobolos.helper.FirebaseHelper;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.Date;

public class User {

    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Date dataNasc;
    private String senha;

    public User (){

    }

    public void salvar (){

        DatabaseReference userRef = FirebaseHelper.getDatabaseReference().child("usuarios").child(getId());
        userRef.setValue(this);

        FirebaseUser user = FirebaseHelper.getAuth().getCurrentUser();

        UserProfileChangeRequest profileUser = new UserProfileChangeRequest.Builder().setDisplayName(getNome()).build();

        if (user != null) user.updateProfile(profileUser);

    }

    public User(String id, String nome, String email, String telefone, String senha, String cpf, Date dataNasc) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
