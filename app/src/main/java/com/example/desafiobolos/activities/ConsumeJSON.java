package com.example.desafiobolos.activities;

import com.example.desafiobolos.models.Cardapio;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumeJSON {

    public static List<Cardapio> jsonDados(String conteudo) {

        try{
            List<Cardapio> cardapioList = new ArrayList<>();
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;

            jsonArray = new JSONArray(conteudo);

            for (int i=0; i<jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                Cardapio cardapio = new Cardapio(null, null, null);

                cardapio.setNome(jsonObject.getString("nome"));
                cardapio.setDesc(jsonObject.getString("desc"));
                cardapio.setValor(jsonObject.getString("valor"));

                cardapioList.add(cardapio);
            }

            return cardapioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
