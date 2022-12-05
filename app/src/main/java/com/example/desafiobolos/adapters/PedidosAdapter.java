package com.example.desafiobolos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiobolos.activities.R;
import com.example.desafiobolos.db.PedidosDB;
import com.example.desafiobolos.models.Pedidos;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public PedidosAdapter(Context context, OnItemClickListener listener)
    {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_delivery, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pedidos delivery = PedidosDB.myOrders.get(position);
        holder.status.setText(delivery.getStatus());
        holder.time.setText(delivery.getTempo());
        holder.itens.setText(delivery.getItens());
    }

    @Override
    public int getItemCount() {
        return PedidosDB.myOrders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView status;
        public TextView time;
        public TextView itens;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status_pedido);
            time = itemView.findViewById(R.id.delivery_time);
            itens = itemView.findViewById(R.id.basket_item);
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
