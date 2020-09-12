package com.putrasamudra.kafein.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;

public class CardViewCafeAdapter extends FirebaseRecyclerAdapter<Cafe, CardViewCafeAdapter.CardViewViewHolder> {

    public CardViewCafeAdapter(@NonNull FirebaseRecyclerOptions<Cafe> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CardViewViewHolder holder, int position, @NonNull Cafe model) {
        Glide.with(holder.itemView.getContext())
                .load(model.getPhoto())
                .apply(new RequestOptions())
                .into(holder.cafePic);
        holder.cafeName.setText(model.getName());
        String rating = Float.toString(model.getRating());
        holder.cafeRating.setText(rating);
        holder.maxPeople.setText(model.getMaxpeople());
    }

    @NonNull
    @Override
    public CardViewCafeAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_cafe, parent, false);
        return new CardViewViewHolder(view);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView cafePic;
        TextView cafeName;
        TextView cafeRating;
        TextView maxPeople;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cafePic = itemView.findViewById(R.id.cafe_pic);
            cafeName = itemView.findViewById(R.id.cafe_name);
            cafeRating = itemView.findViewById(R.id.rating);
            maxPeople = itemView.findViewById(R.id.maxpeople);
        }
    }
}
