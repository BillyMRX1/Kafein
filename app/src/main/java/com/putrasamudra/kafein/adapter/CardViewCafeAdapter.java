package com.putrasamudra.kafein.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.putrasamudra.kafein.MainActivity;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.activity.CafeDetailActivity;
import com.putrasamudra.kafein.model.Cafe;

import java.util.ArrayList;

public class CardViewCafeAdapter extends FirebaseRecyclerAdapter<Cafe, CardViewCafeAdapter.CardViewViewHolder> {
    private Context context;

    public CardViewCafeAdapter(@NonNull FirebaseRecyclerOptions<Cafe> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CardViewViewHolder holder, int position, @NonNull final Cafe model) {
        Glide.with(holder.itemView.getContext())
                .load(model.getPhoto())
                .apply(new RequestOptions())
                .into(holder.cafePic);
        Glide.with(holder.itemView.getContext())
                .load(model.getPhoto2())
                .apply(new RequestOptions())
                .into(holder.cafePic2);
        holder.cafeName.setText(model.getName());
        String rating = Float.toString(model.getRating());
        holder.cafeRating.setText(rating);
        holder.maxPeople.setText(model.getMaxpeople());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cafe cafe = new Cafe();
                cafe.setName(model.getName());
                cafe.setPhoto(model.getPhoto());
                cafe.setPhoto2(model.getPhoto2());
                cafe.setRating(model.getRating());
                cafe.setMaxpeople(model.getMaxpeople());
                cafe.setPosition(model.getPosition());
                Intent cafeDetail = new Intent(context, CafeDetailActivity.class);
                cafeDetail.putExtra(CafeDetailActivity.EXTRA_CAFE, cafe);
                context.startActivity(cafeDetail);
            }
        });
    }

    @NonNull
    @Override
    public CardViewCafeAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_cafe, parent, false);
        return new CardViewViewHolder(view);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView cafePic;
        ImageView cafePic2;
        TextView cafeName;
        TextView cafeRating;
        TextView maxPeople;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cafePic = itemView.findViewById(R.id.cafe_pic);
            cafePic2 = itemView.findViewById(R.id.cafe_pic2);
            cafeName = itemView.findViewById(R.id.cafe_name);
            cafeRating = itemView.findViewById(R.id.rating);
            maxPeople = itemView.findViewById(R.id.maxpeople);
        }
    }
}
