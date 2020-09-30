package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.mubashir.silentvoicefinal.SignUp.isValidEmail;

public class LogIn extends AppCompatActivity
{

    private String email;
    private int password;
    private FirebaseAuth mAuth;
    private TextInputEditText userName;
    private TextInputEditText passText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        userName=(TextInputEditText) findViewById( R.id.usrname );
        passText=(TextInputEditText) findViewById( R.id.usrpass );




        Button buttonSIn = (Button)findViewById(R.id.button2);
        buttonSIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

            confirmation(v);


            }
        });

        Button buttonSUp = (Button)findViewById(R.id.button3);
        buttonSUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(LogIn.this, SignUp.class);
                myIntent.putExtra("key", "LogIn"); //Optional parameters
                LogIn.this.startActivity(myIntent);


            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }




    private void signIn(String email, String password) {


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent myIntent = new Intent(LogIn.this, MainTestActivity.class);
                            myIntent.putExtra("key", "LogIn"); //Optional parameters
                            LogIn.this.startActivity(myIntent);





                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            }

                        if (!task.isSuccessful()) {
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }




    public void confirmation(View view) {

        if((userName.getText().toString().length() <= 0) || (passText.getText().toString().length() <= 0) ) {
            Toast.makeText(LogIn.this, "Fill All Fields",
                    Toast.LENGTH_SHORT).show();

        }
        else {


            email = userName.getText().toString();

            password = Integer.parseInt(passText.getText().toString());


            if (isValidEmail(email)) {


                signIn(email, String.valueOf(password));


            } else {
                Toast.makeText(LogIn.this, "Email Invalid",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }



    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void onBackPressed()
    {
        Intent myIntent = getIntent();
        String previousActivity= myIntent.getStringExtra("key");
        if (previousActivity.equals("SignUp")) {
            myIntent = new Intent( this, LogIn.class );
            startActivity( myIntent );
            finish();
        }
        if (previousActivity.equals("MainSignPage")) {
            myIntent = new Intent( this, MainSignPage.class );
            startActivity( myIntent );
            finish();
        }
    }
}
