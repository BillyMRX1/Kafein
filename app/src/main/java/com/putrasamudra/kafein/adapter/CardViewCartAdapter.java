package com.putrasamudra.kafein.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.model.Cart;

public class CardViewCartAdapter extends FirebaseRecyclerAdapter<Cart, CardViewCartAdapter.CardViewViewHolder> {

    public CardViewCartAdapter(@NonNull FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position, @NonNull Cart model) {
        holder.name.setText(model.getName());
        holder.type.setText(model.getType());
        holder.arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(holder.cardview, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.arrowBtn.setBackgroundResource(R.drawable.ic_arrow_up);
                }else {
                    TransitionManager.beginDelayedTransition(holder.cardview, new AutoTransition());
                    holder.expandableView.setVisibility(View.GONE);
                    holder.arrowBtn.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_cart, parent, false);
        return new CardViewViewHolder(view);
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        ImageView arrowBtn;
        CardView cardview;
        ConstraintLayout expandableView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chair_type);
            type = itemView.findViewById(R.id.date_location);
            arrowBtn = itemView.findViewById(R.id.arrow_btn);
            cardview = itemView.findViewById(R.id.card_view);
            expandableView = itemView.findViewById(R.id.expandable_view);
        }
    }
}
