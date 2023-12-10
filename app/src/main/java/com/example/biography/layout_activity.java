package com.example.biography;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.biography.classess.ImageData;
import com.example.biography.database.db_user_regitseration;
import com.example.biography.databinding.ActivityLayoutBinding;
import com.example.biography.fragments.fragment_add;
import com.example.biography.fragments.fragment_profile;
import com.example.biography.fragments.home_fragment;
import com.google.android.material.navigation.NavigationView;

import java.sql.Blob;

public class layout_activity extends AppCompatActivity {

    db_user_regitseration user_registration;
    String nameuser,lastname;
            int phone;
            byte imgtow;
    int age;
    int id;
    Bitmap imageBitmap;
    Fragment fragment;
    byte[] imgByte;

    ActivityLayoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db_user_regitseration db = new db_user_regitseration(layout_activity.this);
        Cursor dbreuslt = db.selectuserdata();
        while (dbreuslt.moveToNext()) {
            id = dbreuslt.getInt(0);
             nameuser = dbreuslt.getString(1);
            lastname = dbreuslt.getString(2);
            age = dbreuslt.getInt(3);
            phone = dbreuslt.getInt(4);
            imgByte = dbreuslt.getBlob(5);
//            imgtow = imgByte[0];


            Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);

            View header = binding.drawer.getHeaderView(0);
            TextView tvname = (TextView) header.findViewById(R.id.txtnameheader);

            ImageView imageView = (ImageView) header.findViewById(R.id.userimageheader);
            imageView.setImageBitmap(bitmap);
            tvname.setText(nameuser);
        }




        fragment = new home_fragment();
        loadfragment(fragment);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerlayout, binding.toolbar, R.string.open_navigation, R.string.close_navigation);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();


        binding.drawerlayout.closeDrawer(GravityCompat.START);
        binding.drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemid = item.getItemId();


                if (itemid == R.id.btn_add){

                        fragment = new fragment_add();
                        loadfragment(fragment);

                }

                else if (itemid == R.id.btn_profile){
//                    fragment = new fragment_profile();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id",id+"");
//                    bundle.putString("username",nameuser.toString());
//                    bundle.putString("lastname",lastname.toString());
//                    bundle.putInt("age",age);
//                    bundle.putInt("phone",phone);
//                    bundle.putByteArray("image",imgByte);
//                    fragment.setArguments(bundle);
//                    loadfragment(fragment);

                    Intent intent = new Intent(layout_activity.this,profile_activity.class);
//                    intent.putExtra("id",id);
//                    intent.putExtra("username",nameuser);
//                    intent.putExtra("lastname",lastname);
//                    intent.putExtra("age",age);
//                    intent.putExtra("phone",phone);
//                    intent.putExtra("image",imgByte);
                    startActivity(intent);



                } else if (itemid == R.id.btn_home) {
                    fragment = new home_fragment();
                    loadfragment(fragment);
                }


//                switch (item.getItemId()){

//                    case R.id.btn_add:
//                        Toast.makeText(layout_activity.this, "clicked", Toast.LENGTH_SHORT).show();
//                        break;



                        // drawer menu is not working

//                    case R.id.btn_add:
//
//                        fragment = new fragment_add ();
//                        loadfragment(fragment);
//                        binding.drawerlayout.closeDrawer(GravityCompat.START);
//                        break;


//                }






                return true;
            }

        });

    }


        public void loadfragment(Fragment fragment1){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framlayout,fragment).commit();
        binding.drawerlayout.closeDrawer(GravityCompat.START);
    }






    }

















