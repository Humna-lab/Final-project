package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SymbolDetail extends AppCompatActivity {


    private TextView textView_meaning;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_symbol_detail );

        textView_meaning = (TextView) findViewById(R.id.text_meaning);
        img = (ImageView) findViewById(R.id.symbol_image_id);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        int image = intent.getExtras().getInt("Image");

        textView_meaning.setText(Title);
        img.setImageResource(image);


    }
}
