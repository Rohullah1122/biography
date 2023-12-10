package com.example.biography;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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
import java.util.ArrayList;
import java.util.Collections;

public class activity_register extends AppCompatActivity {
    EditText txtname,txtlastname,txtphone;
    Spinner txtage;
    ImageView imguser;
    Button btn_register;
    TextView errorMessage;

    private Bitmap selectedImageBitmap;
    private byte[] imageByteArray;
    int PICK_IMAGE_REQUEST = 1;
    int selected_age = 0;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtname = findViewById(R.id.txtnamee);
        txtlastname = findViewById(R.id.txtlname);
        txtage = findViewById(R.id.txtage);
        txtphone = findViewById(R.id.ptxtphone);
        btn_register = findViewById(R.id.btn_register);
        imguser = findViewById(R.id.userimage);
        errorMessage = findViewById(R.id.error_message);

//        final String spinnermsg[] =
//                {"please select your Age"};
//        ArrayAdapter myAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                android.R.layout.simple_spinner_item, spinnermsg);
//        txtage.setAdapter(myAdapter);


        final String agenumbers[] =
                {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        ArrayAdapter<String> newAdapter = new ArrayAdapter<>(activity_register.this, android.R.layout.simple_spinner_item, agenumbers);
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the new adapter to the Spinner
        txtage.setAdapter(newAdapter);


        txtage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_age = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });






        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });













        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,lname,phone,photo,age;
                name = txtname.getText().toString();
                lname = txtlastname.getText().toString();
                phone = txtphone.getText().toString();

                nullmethod(name,lname,phone);



                if (selectedImageBitmap != null) {
                    // Convert the selected image to a byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageByteArray = stream.toByteArray();

                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Insert the image into the database
//                    database.execSQL("INSERT INTO Images (image) VALUES (?)", new Object[]{imageByteArray});
                    // Reset the image view and selected image
                    imguser.setImageBitmap(null);
                    selectedImageBitmap = null;
                }else {
                    imguser.setImageResource(R.drawable.noimage);
                }



                db_user_regitseration db_registration = new db_user_regitseration(activity_register.this);
                int parsephone = Integer.parseInt(phone);
                long resut = db_registration.insert(name,lname,selected_age,parsephone,imageByteArray);
                if (resut != -1){

                    Toast.makeText(activity_register.this, "successfully inserted", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(activity_register.this, "unsucessful insertion", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }


    public String nullmethod(String name,String lname,String phone){
        name = txtname.getText().toString();
        lname = txtlastname.getText().toString();
        phone = txtphone.getText().toString();

        if (name.isEmpty()){
            txtname.setError("enter name");
            txtname.requestFocus();
            return name;
        }
        if (lname.isEmpty()){
            txtlastname.setError("enter last name");
            txtlastname.requestFocus();
            return lname;
        }
        if (phone.isEmpty()){
            txtphone.setError("enter phone number");
            txtname.requestFocus();
            return phone;
        }


        if (txtage.getSelectedItemPosition() != Spinner.INVALID_POSITION) {
            // The user has selected an item, do something with it
            String selectedOption = txtage.getSelectedItem().toString();
            errorMessage.setVisibility(View.GONE);
            // Perform the desired action with the selected option
            // ...
        } else {
            // The user has not selected an item, show an error message
            errorMessage.setVisibility(View.VISIBLE);
            errorMessage.setText("Please select an option");
            return errorMessage.toString();
        }




        return null;









    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                // Convert the selected image to a bitmap
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                // Display the selected image in the ImageView
                imguser.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}