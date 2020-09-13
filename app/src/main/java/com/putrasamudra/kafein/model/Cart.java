package com.putrasamudra.kafein.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private String chair;
    private String cafe;
    private int date;
    private int time;

    public Cart(){
    }

    protected Cart(Parcel in){
        chair = in.readString();
        cafe = in.readString();
        date = in.readInt();
        time = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public String getChair() { return chair; }

    public void setChair(String chair) { this.chair = chair; }

    public String getCafe() { return cafe; }

    public void setCafe(String cafe) { this.cafe = cafe; }

    public int getDate() { return date; }

    public void setDate(int date) { this.date = date; }

    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }


    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(chair);
        parcel.writeString(cafe);
        parcel.writeInt(date);
        parcel.writeInt(time);
    }
}
