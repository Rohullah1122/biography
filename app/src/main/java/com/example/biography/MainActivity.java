package com.example.biography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.biography.database.db_user_regitseration;



public class MainActivity extends AppCompatActivity {




    private static final long DELAY_TIME = 3000; // 5 seconds delay


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create a Handler
        Handler handler = new Handler();

        // Define a Runnable that will start the new activity
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Start the new activity here
                db_user_regitseration dbregister = new db_user_regitseration(MainActivity.this);
                boolean check =  dbregister.checktable();

                if (check == true){
                    Intent intent = new Intent(MainActivity.this,activity_register.class);
                    startActivity(intent);


                }else{

                    Intent intent = new Intent(MainActivity.this,layout_activity.class);
                    startActivity(intent);

                }
                finish(); // Optional: finish the current activity if needed
            }
        };

        // Post the runnable with a delay
        handler.postDelayed(runnable, DELAY_TIME);
    }











    }






