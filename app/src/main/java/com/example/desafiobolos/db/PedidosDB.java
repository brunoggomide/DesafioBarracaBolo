package com.example.desafiobolos.db;

import com.example.desafiobolos.models.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidosDB {

    public static List<Pedidos> myOrders = new ArrayList<>();

    static {
        myOrders.add(new Pedidos("Preparando o pedido...", "Previsão de entrega: 18:25 - 18:35", "1 Bolo de Laranja"));
        myOrders.add(new Pedidos("Pedido saiu para a entrega...", "Previsão de entrega: 17:45 - 17:55", "1 Bolo de Cenoura"));
        myOrders.add(new Pedidos("Pedido concluido...", "Entregue", "1 Bolo de Chocolate"));
    }

}
