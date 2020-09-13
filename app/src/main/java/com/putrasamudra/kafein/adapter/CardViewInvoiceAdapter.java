package com.putrasamudra.kafein.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.model.Cafe;
import com.putrasamudra.kafein.model.Invoice;

public class CardViewInvoiceAdapter extends FirebaseRecyclerAdapter<Invoice, CardViewInvoiceAdapter.CardViewViewHolder> {
    public CardViewInvoiceAdapter(@NonNull FirebaseRecyclerOptions<Invoice> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CardViewViewHolder holder, int position, @NonNull Invoice model) {
        holder.noinvoice.setText(model.getNoinvoice());
        holder.price.setText(model.getPrice());
        holder.name.setText(model.getName());
        holder.datelocation.setText(model.getDatelocation());
        holder.status.setText(model.getStatus());
        holder.payment.setText(model.getPayment());
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_akun_pesanan, parent, false);
        return new CardViewViewHolder(view);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView noinvoice;
        TextView price;
        TextView name;
        TextView datelocation;
        TextView status;
        TextView payment;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            noinvoice = itemView.findViewById(R.id.akun_pesanan_no_invoice);
            price = itemView.findViewById(R.id.akun_pesanan_harga);
            name = itemView.findViewById(R.id.akun_pesanan_kursi);
            datelocation = itemView.findViewById(R.id.akun_pesanan_cafe);
            status = itemView.findViewById(R.id.akun_pesanan_status_bayar);
            payment = itemView.findViewById(R.id.akun_pesanan_metode_bayar);
        }
    }
}
