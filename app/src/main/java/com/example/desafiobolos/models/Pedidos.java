package com.example.desafiobolos.models;

import java.io.Serializable;

public class Pedidos implements Serializable {
    private String status;
    private String tempo;
    private String itens;

    public Pedidos(String status, String tempo, String itens) {
        this.status = status;
        this.tempo = tempo;
        this.itens = itens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }
}
