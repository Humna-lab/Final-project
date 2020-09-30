package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {


    GridView androidGridView;

    String[] gridViewString = {
            "ASL to ENG", "ENG to ASL", "ASL Dictionary",

    } ;
    int[] gridViewImageId = {
            R.drawable.transicon3, R.drawable.transicon2, R.drawable.dict1,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(MainScreen.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(MainScreen.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();


                if( gridViewString[+i]=="ENG to ASL"){
                    Intent myIntent = new Intent(MainScreen.this, EngToAsl.class);
                    myIntent.putExtra("key", 5); //Optional parameters
                    MainScreen.this.startActivity(myIntent);
                    finish();

                }

                if( gridViewString[+i]=="ASL Dictionary"){
                    Intent myIntent = new Intent(MainScreen.this, DictSymbol.class);
                    myIntent.putExtra("key", "MainScreen"); //Optional parameters
                    MainScreen.this.startActivity(myIntent);
                    finish();
                }
                if( gridViewString[+i]=="ASL to ENG"){
                    Intent myIntent = new Intent(MainScreen.this, MainScreen.class);
                    myIntent.putExtra("key", "MainScreen"); //Optional parameters
                    MainScreen.this.startActivity(myIntent);
                    finish();
                }



            }


        });


    }

}
