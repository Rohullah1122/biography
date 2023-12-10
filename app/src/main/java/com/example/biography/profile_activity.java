package com.example.biography;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biography.database.db_user_regitseration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class profile_activity extends AppCompatActivity {
    ImageView imgprogile;
    EditText txtname,txtlname,txtphone;
    TextView error_message;
    Spinner spage;
    Button btn_update;
    ArrayAdapter<String> currentAdapter;
    Uri imageuri;
    private byte[] imageByteArray;
    byte[]  image;
    private Bitmap selectedImageBitmap;
    int id,phone;
    String name,lastname;
    int age;
    int updateage;
    String ageconvert;
    Bitmap bitmap;
    int agee[];
    String agetow;
    db_user_regitseration db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        txtname =  findViewById(R.id.txtnaamupdate);
        txtlname = findViewById(R.id.txtlnameupdate);
        spage =  findViewById(R.id.txtageupdate);
        txtphone = findViewById(R.id.ptxtphoneupdate);
        btn_update = findViewById(R.id.btn_profileupdate);
        imgprogile = findViewById(R.id.userimageupdate);

        error_message = findViewById(R.id.error_message);


         db = new db_user_regitseration(profile_activity.this);
        Cursor dbreuslt = db.selectuserdata();
        while (dbreuslt.moveToNext()) {
            id = dbreuslt.getInt(0);
            name = dbreuslt.getString(1);
            lastname = dbreuslt.getString(2);
            age = dbreuslt.getInt(3);
            phone = dbreuslt.getInt(4);
            image = dbreuslt.getBlob(5);


            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                txtlname.setText(lastname);
                txtname.setText(name);
                txtphone.setText(phone+"");
                imgprogile.setImageBitmap(bitmap);

            int[] agetow = {age};
            int i = agetow[0];
            ageconvert = String.valueOf(i);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(profile_activity.this, android.R.layout.simple_spinner_dropdown_item, ageconvert.split(","));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spage.setAdapter(adapter);
        }


        final String numbers[] =
                {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        currentAdapter = new ArrayAdapter<>(profile_activity.this, android.R.layout.simple_spinner_item, numbers);

        spage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spage.setAdapter(null);
                spage.setAdapter(currentAdapter);
                spage.setSelection(position);
                updateage = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imgprogile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,lname;
                        int sal,mobile;
                name = txtname.getText().toString();
                lname = txtlname.getText().toString();
                mobile = Integer.parseInt(txtphone.getText().toString());
                sal = updateage + 1;
                nullmethod(name,lname,mobile+"");


                // i jhave to compress

//                selectedImageBitmap.compress(bitmap.compress(Bitmap.CompressFormat.PNG,100,stream));

                if (selectedImageBitmap != null){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageByteArray = stream.toByteArray();

                    long result =
                            db.update(id,name,lname,sal,mobile,imageByteArray);

                    if (result  != -1){

                        Cursor dbreuslt = db.selectuserdata();
                        while (dbreuslt.moveToNext()) {
                            id = dbreuslt.getInt(0);
                            name = dbreuslt.getString(1);
                            lastname = dbreuslt.getString(2);
                            age = dbreuslt.getInt(3);
                            phone = dbreuslt.getInt(4);
                            image = dbreuslt.getBlob(5);


                            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                            txtlname.setText(lastname);
                            txtname.setText(name);
                            txtphone.setText(phone+"");
                            imgprogile.setImageBitmap(bitmap);

                            int[] agetow = {age};
                            int i = agetow[0];
                            ageconvert = String.valueOf(i);

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(profile_activity.this, android.R.layout.simple_spinner_dropdown_item, ageconvert.split(","));
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spage.setAdapter(adapter);
                        }



                    }else{


                        Toast.makeText(profile_activity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

                    }




                }
                else if(selectedImageBitmap == null){

                    long result =
                            db.update(id,name,lname,sal,mobile,image);

                    if (result  != -1){

                        Cursor dbreuslt = db.selectuserdata();
                        while (dbreuslt.moveToNext()) {
                            id = dbreuslt.getInt(0);
                            name = dbreuslt.getString(1);
                            lastname = dbreuslt.getString(2);
                            age = dbreuslt.getInt(3);
                            phone = dbreuslt.getInt(4);
                            image = dbreuslt.getBlob(5);


                            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                            txtlname.setText(lastname);
                            txtname.setText(name);
                            txtphone.setText(phone+"");
                            imgprogile.setImageBitmap(bitmap);

                            int[] agetow = {age};
                            int i = agetow[0];
                            ageconvert = String.valueOf(i);

                            // i have to work with age updating

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(profile_activity.this, android.R.layout.simple_spinner_dropdown_item, ageconvert.split(","));
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spage.setAdapter(adapter);
                        }



                    }else{


                        Toast.makeText(profile_activity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

                    }




                }








            }
        });








    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null){
            imageuri = data.getData();
            try {
                // Convert the selected image to a bitmap
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                // Display the selected image in the ImageView
                imgprogile.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }






        // its not selecting the image
        }



    }


    public String nullmethod(String name,String lname,String phone){
        name = txtname.getText().toString();
        lname = txtlname.getText().toString();
        phone = txtphone.getText().toString();

        if (name.isEmpty()){
            txtname.setError("enter name");
            txtname.requestFocus();
            return name;
        }
        if (lname.isEmpty()){
            txtlname.setError("enter last name");
            txtlname.requestFocus();
            return lname;
        }
        if (phone.isEmpty()){
            txtphone.setError("enter phone number");
            txtname.requestFocus();
            return phone;
        }


        if (spage.getSelectedItemPosition() != Spinner.INVALID_POSITION) {
            // The user has selected an item, do something with it
            String selectedOption = spage.getSelectedItem().toString();
            error_message.setVisibility(View.GONE);
            // Perform the desired action with the selected option
            // ...
        } else {
            // The user has not selected an item, show an error message
            error_message.setVisibility(View.VISIBLE);
            error_message.setText("Please select an option");
            return error_message.toString();
        }




        return null;









    }
}