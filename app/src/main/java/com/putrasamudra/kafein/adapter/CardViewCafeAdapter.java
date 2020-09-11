package com.putrasamudra.kafein.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;

public class CardViewCafeAdapter extends RecyclerView.Adapter<CardViewCafeAdapter.CardViewViewHolder> {
    private ArrayList<Cafe> listCafe;

    public CardViewCafeAdapter(ArrayList<Cafe> list) {
        this.listCafe = list;
    }

    @NonNull
    @Override
    public CardViewCafeAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_cafe, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewCafeAdapter.CardViewViewHolder holder, int position) {
        Cafe cafe = listCafe.get(position);
        Glide.with(holder.itemView.getContext())
                .load(cafe.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.cafePic);
        holder.cafeName.setText(cafe.getName());
        holder.cafeRating.setRating(cafe.getRating());
    }

    @Override
    public int getItemCount() {
        return listCafe.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView cafePic;
        TextView cafeName;
        RatingBar cafeRating;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cafePic = itemView.findViewById(R.id.cafe_pic);
            cafeName = itemView.findViewById(R.id.cafe_name);
            cafeRating = itemView.findViewById(R.id.cafe_rating);
        }
    }
}
