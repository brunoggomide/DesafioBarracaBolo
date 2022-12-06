package com.example.desafiobolos.models;

import java.io.Serializable;

public class Cardapio implements Serializable {
    private String nome;
    private String desc;
    private String valor;
    private String url;

    public Cardapio(String nome, String desc, String valor, String url) {
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
