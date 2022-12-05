package com.example.desafiobolos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.db.CardapioDB;
import com.example.desafiobolos.models.Cardapio;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public CardapioAdapter(Context context, OnItemClickListener listener)
    {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_menu, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cardapio cardapio = CardapioDB.myMenu.get(position);
        holder.produto.setText(cardapio.getNome());
        holder.preco.setText(cardapio.getValor());
    }

    @Override
    public int getItemCount() {
        return CardapioDB.myMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView produto;
        public TextView preco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            produto = itemView.findViewById(R.id.menu_name);
            preco = itemView.findViewById(R.id.menu_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
