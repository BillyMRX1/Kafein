package com.putrasamudra.kafein.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cafe implements Parcelable {
    private String name;
    private String photo;
    private String photo2;
    private String maxpeople;
    private String position;
    private float rating;

    public Cafe(){
    }

    protected Cafe(Parcel in) {
        name = in.readString();
        photo = in.readString();
        photo2 = in.readString();
        maxpeople = in.readString();
        position = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<Cafe> CREATOR = new Creator<Cafe>() {
        @Override
        public Cafe createFromParcel(Parcel in) {
            return new Cafe(in);
        }

        @Override
        public Cafe[] newArray(int size) {
            return new Cafe[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getMaxpeople() {
        return maxpeople;
    }

    public void setMaxpeople(String maxpeople) {
        this.maxpeople = maxpeople;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(photo);
        parcel.writeString(photo2);
        parcel.writeString(maxpeople);
        parcel.writeString(position);
        parcel.writeFloat(rating);
    }
}
