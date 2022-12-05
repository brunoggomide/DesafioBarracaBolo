package com.example.desafiobolos.db;

import com.example.desafiobolos.models.Cardapio;

import java.util.ArrayList;
import java.util.List;

public class CardapioDB {

    public static List<Cardapio> myMenu = new ArrayList<>();

    static {
        myMenu.add(new Cardapio("Bolo de Cenoura", "com cobertura de chocolate", "29.90"));
        myMenu.add(new Cardapio("Bolo de Coco", "com coco ralado", "24.90"));
        myMenu.add(new Cardapio("Bolo de Laranja", "sem cobertura", "19.90"));
        myMenu.add(new Cardapio("Bolo de Chocolate", "com cobertura de brigadeiro", "28.90"));
        myMenu.add(new Cardapio("Bolo de Milho", "sem cobertura", "23.90"));
        myMenu.add(new Cardapio("Bolo Formigueiro", "com cobertura de chocolate", "21.90"));
        myMenu.add(new Cardapio("Bolo de Ninho", "com cobertura de brigadeiro de ninho", "32.90"));
        myMenu.add(new Cardapio("Bolo de Fuba", "sem cobertura", "17.90"));
    }
}
