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


public class SignUp extends AppCompatActivity {


    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private String email;
    private int password;
    private String Strpassword;
    private String strconfpassword;
    private int confpassword;
    private TextInputEditText userName;
    private TextInputEditText passText;
    private TextInputEditText confpassText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName=(TextInputEditText) findViewById( R.id.email );
        passText=(TextInputEditText) findViewById( R.id.pass );
        confpassText=(TextInputEditText) findViewById( R.id.confpass );


        Button buttonconf = (Button)findViewById(R.id.confbutton);
        buttonconf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                confirmation( v );

            }
        });




        Button buttonSIn = (Button)findViewById(R.id.button3);
        buttonSIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent myIntent = new Intent(SignUp.this, LogIn.class);
                myIntent.putExtra("key", "SignUp"); //Optional parameters
                SignUp.this.startActivity(myIntent);
                finish();

            }
        });


    }

    public void onBackPressed()
    {
        Intent myIntent = getIntent();
        String previousActivity= myIntent.getStringExtra("key");
        if (previousActivity.equals("LogIn")) {
            myIntent = new Intent( this, MainSignPage.class );
            startActivity( myIntent );
            finish();
        }
        if (previousActivity.equals("MainSignPage")) {
            myIntent = new Intent( this, MainSignPage.class );
            startActivity( myIntent );
            finish();
        }
    }




    public void confirmation(View view) {
        email=userName.getText().toString();

        Strpassword=passText.getText().toString();

        strconfpassword=confpassText.getText().toString();




        if((userName.getText().toString().length() <= 0) || (passText.getText().toString().length() <= 0) || (confpassText.getText().toString().length() <= 0)) {
            Toast.makeText(SignUp.this, "Fill All Fields",
                    Toast.LENGTH_SHORT).show();

        }
        else
        {
            password= Integer.parseInt(passText.getText().toString());
            confpassword= Integer.parseInt(confpassText.getText().toString());
            if (isValidEmail(email)) {
                if (password == confpassword) {


                    createAccount(email, String.valueOf(password));


                } else {
                    Toast.makeText(SignUp.this, "Password Does not match.",
                            Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(SignUp.this, "Email Invalid",
                        Toast.LENGTH_SHORT).show();

            }
        }


    }




    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void createAccount(final String email, String password) {
        Log.d(TAG, "createAccount:" + email);


        mAuth = FirebaseAuth.getInstance();
        // [START create_user_with_email]

        mAuth.createUserWithEmailAndPassword(email,String.valueOf( password))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override              public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent myIntent = new Intent(SignUp.this, MainSignPage.class);
                            myIntent.putExtra("key", "SignUp"); //Optional parameters
                            SignUp.this.startActivity(myIntent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed."  ,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }

//
//    private void signIn(String email, String password) {
//        Log.d(TAG, "signIn:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(SignUp.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        if (!task.isSuccessful()) {
//                            mStatusTextView.setText(R.string.auth_failed);
//                        }
//                    }
//                });
//    }
//
//    private void signOut() {
//        mAuth.signOut();
//        updateUI(null);
//    }
//
//    private void sendEmailVerification() {
//        // Disable button
//        findViewById(R.id.verify_email_button).setEnabled(false);
//
//        // Send verification email
//        // [START send_email_verification]
//        final FirebaseUser user = mAuth.getCurrentUser();
//        user.sendEmailVerification()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // [START_EXCLUDE]
//                        // Re-enable button
//                        findViewById(R.id.verify_email_button).setEnabled(true);
//
//                        if (task.isSuccessful()) {
//                            Toast.makeText(EmailPasswordActivity.this,
//                                    "Verification email sent to " + user.getEmail(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Log.e(TAG, "sendEmailVerification", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this,
//                                    "Failed to send verification email.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    private boolean validateForm() {
//        boolean valid = true;
//
//        String email = mEmailField.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            mEmailField.setError("Required.");
//            valid = false;
//        } else {
//            mEmailField.setError(null);
//        }
//
//        String password = mPasswordField.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            mPasswordField.setError("Required.");
//            valid = false;
//        } else {
//            mPasswordField.setError(null);
//        }
//
//        return valid;
//    }
//
//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
//                    user.getEmail(), user.isEmailVerified()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
//            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
//            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);
//
//            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
//        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
//            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
//            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onClick(View v)
// {
//        int i = v.getId();
//        if (i == R.id.email_create_account_button) {
//            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
//        } else if (i == R.id.email_sign_in_button) {
//            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
//        } else if (i == R.id.sign_out_button) {
//            signOut();
//        } else if (i == R.id.verify_email_button) {
//            sendEmailVerification();
//        }
//    }

}
