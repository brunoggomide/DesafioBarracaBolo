package com.example.desafiobolos.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static String DB_NAME = "DB_DESAFIO";

    private static final String TABELA_ITEM_PEDIDO = "item_pedido";
    private static final String TABLE_ENTREGA = "entrega";
    private static final String TABELA_EMPRESA = "empresa";

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_ID_FIREBASE = "id_firebase";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_VALOR = "valor";
    private static final String COLUNA_URL_IMAGEM = "url_imagem";
    private static final String COLUNA_QUANTIDADE = "quantidade";
    private static final String COLUNA_TAXA_ENTREGA = "taxa_entrega";
    private static final String COLUNA_FORMA_PAGAMENTO = "forma_pagamento";
    private static final String COLUNA_ENDERECO_LOGRADOROURO = "logradouro";
    private static final String COLUNA_ENDERECO_BAIRRO = "bairro";
    private static final String COLUNA_ENDERECO_MUNICIPIO = "municipio";




    public DbHelper(Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_EMPRESA = " CREATE TABLE IF NOT EXISTS " + TABELA_EMPRESA
                + " (id_firebase TEXT NOT NULL, " +
                " nome TEXT NOT NULL, " +
                " taxa_entrega DOUBLE NOT NULL, " +
                " tempo_minimo INTEGER NOT NULL, " +
                " tempo_maximo INTEGER NOT NULL, " +
                " url_imagem TEXT NOT NULL); ";

        String TABLE_ITEM_PEDIDO = " CREATE TABLE IF NOT EXISTS " + TABELA_ITEM_PEDIDO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " id_firebase TEXT NOT NULL, " +
                " nome TEXT NOT NULL, " +
                " valor DOUBLE NOT NULL, " +
                " url_imagem TEXT NOT NULL, " +
                " quantidade INTEGER NOT NULL); ";

        String TABLE_TAXA_ENTREGA = " CREATE TABLE IF NOT EXISTS " + TABLE_ENTREGA
                + " (forma_pagamento TEXT NOT NULL, " +
                " logradouro TEXT NOT NULL, " +
                " bairro TEXT NOT NULL, " +
                " municipio TEXT NOT NULL, " +
                " quantidade INTEGER NOT NULL); ";

                try {
                    sqLiteDatabase.execSQL(TABLE_EMPRESA);
                    sqLiteDatabase.execSQL(TABLE_ITEM_PEDIDO);
                    sqLiteDatabase.execSQL(TABLE_TAXA_ENTREGA);

                    Log.i("INFO_DB:", "onCreate: TABELA CRIADA COM SUCESSO ");

                }catch (Exception e) {
                    Log.i("INFOR_DB", "onCreate: ERRO AO CRIAR TABELA");

                }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
