package com.putrasamudra.kafein.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.putrasamudra.kafein.R;
import com.putrasamudra.kafein.model.Cafe;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class CafeDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public static final String EXTRA_CAFE = "extra_cafe";
    private TextView dateInput;
    private ImageView thumbnail;
    private TextView cafeName;
    private TextView ratingTxt;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);
        dateInput = findViewById(R.id.date_input);
        thumbnail = findViewById(R.id.cafe_thumbnail);
        cafeName = findViewById(R.id.cafe_name);
        ratingTxt = findViewById(R.id.rating_text);
        ratingBar = findViewById(R.id.ratingBar);

        Cafe cafe = this.getIntent().getParcelableExtra(EXTRA_CAFE);

        Glide.with(this)
                .load(cafe.getPhoto())
                .apply(new RequestOptions())
                .into(thumbnail);
        cafeName.setText(cafe.getName());
        String rating = String.valueOf(cafe.getRating());
        ratingTxt.setText(rating);
        ratingBar.setRating(cafe.getRating());

        setDropdown();
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
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
}