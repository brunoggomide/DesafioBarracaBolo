package com.example.desafiobolos.models;

import java.io.Serializable;
import java.sql.Blob;

public class Cardapio implements Serializable {
    private String nome;
    private String desc;
    private String valor;

    public Cardapio(String nome, String desc, String valor) {
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
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
}
