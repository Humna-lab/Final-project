package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainTestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView androidGridView;
    private FirebaseAuth mAuth;
    String[] gridViewString = {
            "ASL to ENG", "ASL Dictionary",

    } ;
    int[] gridViewImageId = {
            R.drawable.transicon3,  R.drawable.dict1,

    };


    String name;
    String email;
    Uri photoUrl;

    boolean emailVerified;

    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);


        mAuth = FirebaseAuth.getInstance();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Welcome to Silent Voice", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//


        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(MainTestActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
             //   Toast.makeText(MainTestActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();

                if( gridViewString[+i]=="ASL to ENG"){
                    Intent myIntent = new Intent(MainTestActivity.this, CameraActivity.class);
                    myIntent.putExtra("key", "MainTestActivity"); //Optional parameters
                    MainTestActivity.this.startActivity(myIntent);
                    finish();

                }
                if( gridViewString[+i]=="ENG to ASL"){
                    Intent myIntent = new Intent(MainTestActivity.this, EngToAsl.class);
                    myIntent.putExtra("key", "MainTestActivity"); //Optional parameters
                    MainTestActivity.this.startActivity(myIntent);
                    finish();
                }
                if( gridViewString[+i]=="ASL Dictionary"){
                    Intent myIntent = new Intent(MainTestActivity.this, DictSymbol.class);
                    myIntent.putExtra("key", "MainTestActivity"); //Optional parameters
                    MainTestActivity.this.startActivity(myIntent);
                    finish();
                }


            }


        });





    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        getCurrentUser();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.navUsername);
        navUsername.setText(email );
        TextView naveml = (TextView) headerView.findViewById(R.id.naveml);
        naveml.setText("");

        ImageView img = (ImageView) headerView.findViewById(R.id.navimg);
        img.setImageResource(R.drawable.silentvoice21_30);




    }
        private void signOut() {
        mAuth.signOut();
        Intent myIntent = new Intent(MainTestActivity.this, MainSignPage.class);
//        myIntent.putExtra("key", "SignUp"); //Optional parameters
        MainTestActivity.this.startActivity(myIntent);
        finish();
    }

    public void getCurrentUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        Intent myIntent = getIntent();
//        String previousActivity= myIntent.getStringExtra("key");
//        if (previousActivity.equals("SignIn")) {
//            myIntent = new Intent( this, LogIn.class );
//            startActivity( myIntent );
//        }
//        if (previousActivity.equals("MainSignPage")) {
//            myIntent = new Intent( this, MainSignPage.class );
//            startActivity( myIntent );
//        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout) {
            // Handle the camera action
            signOut();
        } else if (id == R.id.contact)
        {

        } else if (id == R.id.about)
        {


        } else if (id == R.id.rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
