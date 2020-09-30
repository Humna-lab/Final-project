package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainSignPage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_sign_page );


        mAuth = FirebaseAuth.getInstance();
        onStart();
        Button buttonSIn = (Button)findViewById(R.id.button5);
        buttonSIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(MainSignPage.this, LogIn.class);
                myIntent.putExtra("key", "MainSignPage"); //Optional parameters
                MainSignPage.this.startActivity(myIntent);
                finish();

            }
        });

        Button buttonSUp = (Button)findViewById(R.id.button6);
        buttonSUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(MainSignPage.this, SignUp.class);
                myIntent.putExtra("key", "MainSignPage"); //Optional parameters
                MainSignPage.this.startActivity(myIntent);
                finish();

            }
        });

        Button buttonSInL = (Button)findViewById(R.id.button7);
        buttonSInL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(MainSignPage.this, MainTestActivity.class);
                myIntent.putExtra("key", "MainSignPage"); //Optional parameters
                MainSignPage.this.startActivity(myIntent);
                finish();

            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
        if (currentUser!=null)
        {
            Intent myIntent = new Intent(MainSignPage.this, MainTestActivity.class);
            myIntent.putExtra("key", "MainSignPage"); //Optional parameters
            MainSignPage.this.startActivity(myIntent);
            finish();
        }

    }



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
