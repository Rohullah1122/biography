package com.example.biography.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.biography.R;
import com.example.biography.database.db_user_regitseration;

import java.net.URL;


public class fragment_profile extends Fragment {

    ImageView imgprogile;
    EditText txtname,txtlname,txtphone;
    Spinner spage;
    Button btn_update;
    String [] adabter;
    ArrayAdapter<String> currentAdapter;
    Uri img;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance(String param1, String param2) {
        fragment_profile fragment = new fragment_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        int id,phone;
        String name,lastname;
                int age;


        Bitmap bitmap;
        byte[]  image;
        byte [] ax;
        String ageconvert;



        txtname =  view.findViewById(R.id.txtnaamupdate);
        txtlname = view.findViewById(R.id.txtlnameupdate);
        spage = view.findViewById(R.id.txtageupdate);
        txtphone = view.findViewById(R.id.ptxtphoneupdate);
        btn_update = view.findViewById(R.id.btn_profileupdate);
        imgprogile = view.findViewById(R.id.userimageupdate);
        db_user_regitseration rg = new db_user_regitseration(getActivity());
        id = getArguments().getInt("id");
        name = getArguments().getString("username");
        lastname = getArguments().getString("lastname");
        phone = getArguments().getInt("phone");
        age = getArguments().getInt("age");
        image = getArguments().getByteArray("image");
        int[] agetow = {age};
        // i have to work in image now


        imgprogile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("*/image");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });

        ageconvert = agetow.toString();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ageconvert.split(","));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spage.setAdapter(adapter);

//        txtphone.setText(phone);
        txtname.setText(lastname);
        txtlname.setText(name);
//        txtphone.setText(phone);
//        spage.setAdapter(adapter);
        bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imgprogile.setImageBitmap(bitmap);







        final String numbers[] =
                {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
         currentAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, numbers);

        spage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spage.setAdapter(null);
                spage.setAdapter(currentAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });











        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {










            }
        });









        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data !=null ){
            img = data.getData();
            imgprogile.setImageURI(img);



        }
    }
}