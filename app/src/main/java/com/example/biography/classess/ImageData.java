package com.example.biography.classess;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageData implements Parcelable {
    private byte[] imageData;

    public ImageData(Parcel in) {
        imageData = in.createByteArray();
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(imageData);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
