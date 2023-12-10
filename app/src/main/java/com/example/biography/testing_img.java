package com.example.biography;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biography.database.db_user_regitseration;

public class testing_img extends AppCompatActivity {
        ImageView img;
        TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_img);
        img = findViewById(R.id.userimagetester);
        tv = findViewById(R.id.txtnameheadertester);

        db_user_regitseration db = new db_user_regitseration(testing_img.this);
        Cursor dbreuslt = db.selectuserdata();
        while (dbreuslt.moveToNext()){
            String name = dbreuslt.getString(1);
            byte[] imgByte = dbreuslt.getBlob(5);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            img.setImageBitmap(bitmap);
            tv.setText(name);






        }


















    }
}