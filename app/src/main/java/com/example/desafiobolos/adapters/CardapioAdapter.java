package com.example.desafiobolos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.models.Cardapio;
import com.bumptech.glide.Glide;

import java.util.List;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.CardapioViewHolder>{

    List<Cardapio> cardapioList;
    Context context;

    public CardapioAdapter(Context context, List<Cardapio> cardapios) {
        this.context = context;
        cardapioList = cardapios;
    }

    @NonNull
    @Override
    public CardapioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new CardapioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardapioViewHolder holder, int position) {
        Cardapio cardapio = cardapioList.get(position);
        holder.produto.setText(cardapio.getNome());
        holder.preco.setText(cardapio.getValor());
        Glide.with(holder.foto)
                .load(cardapio.getImageURL())
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return cardapioList.size();
    }

    public class CardapioViewHolder extends RecyclerView.ViewHolder{
        TextView produto, preco;
        ImageView foto;

        public CardapioViewHolder(@NonNull View itemView) {
            super(itemView);

            produto = itemView.findViewById(R.id.menu_name);
            preco = itemView.findViewById(R.id.menu_price);
            foto = itemView.findViewById(R.id.foto);
        }
    }
}
