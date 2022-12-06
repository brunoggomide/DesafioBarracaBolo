package com.example.desafiobolos.models;

import java.io.Serializable;

public class Cardapio implements Serializable {
    private String nome;
    private String description;
    private String valor;
    private String imageURL;

    public Cardapio(String nome, String description, String valor, String imageURL) {
        this.nome = nome;
        this.description = description;
        this.valor = valor;
        this.imageURL = imageURL;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
