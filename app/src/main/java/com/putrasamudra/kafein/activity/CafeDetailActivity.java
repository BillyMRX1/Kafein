package com.putrasamudra.kafein.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.adapter.CardViewCartAdapter;
import com.putrasamudra.kafein.model.Cafe;
import com.putrasamudra.kafein.model.Cart;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class CafeDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public static final String EXTRA_CAFE = "extra_cafe";
    private TextView dateInput;
    private CardViewCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);
        dateInput = findViewById(R.id.date_input);
        ImageView thumbnail = findViewById(R.id.cafe_thumbnail);
        TextView cafeName = findViewById(R.id.cafe_name);
        TextView ratingTxt = findViewById(R.id.rating_text);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        Cafe cafe = this.getIntent().getParcelableExtra(EXTRA_CAFE);

        Glide.with(this)
                .load(cafe.getPhoto())
                .apply(new RequestOptions())
                .into(thumbnail);
        cafeName.setText(cafe.getName());
        String rating = String.valueOf(cafe.getRating());
        ratingTxt.setText(rating);
        ratingBar.setRating(cafe.getRating());
        String position = cafe.getPosition();

        setDropdown();
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        DatabaseReference mbase = FirebaseDatabase.getInstance().getReference().child("Cafe").child(position).child("cart");
        RecyclerView rvTypeSeat = findViewById(R.id.rv_cart);
        rvTypeSeat.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(mbase, Cart.class).build();
        adapter = new CardViewCartAdapter(options);
        rvTypeSeat.setAdapter(adapter);
    }

    private void setDropdown(){
        String[] SEAT = new String[] {"1 - 3  People", "Item 2", "Item 3", "Item 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(CafeDetailActivity.this, R.layout.dropdown_menu_popup_item, SEAT);

        AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.reserve_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setText(SEAT[0],false);
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String dd, mm;
        if (dayOfMonth < 10){
            dd = "0" + dayOfMonth;
        }else {
            dd = "" + dayOfMonth;
        }
        if ((month+1) < 10){
            mm = "0" + (month+1);
        }else {
            mm = "" + (month+1);
        }
        String date = dd + "-" + mm + "-" + year;
        dateInput.setText(date);
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}