package com.example.mubashir.silentvoicefinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DictSymbol extends AppCompatActivity {


    SearchView searchView;
    List<SymbolText> list_of_text;
    private RecyclerViewAdapter _myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dict_symbol );


        list_of_text = new ArrayList<>();
        list_of_text.add(new SymbolText("A",R.drawable.a_s, R.drawable.large_a));
        list_of_text.add(new SymbolText("B",R.drawable.b_s,R.drawable.large_b));
        list_of_text.add(new SymbolText("C",R.drawable.c_s,R.drawable.large_c));
        list_of_text.add(new SymbolText("D",R.drawable.d_s,R.drawable.large_d));
        list_of_text.add(new SymbolText("E",R.drawable.e_s,R.drawable.large_e));
        list_of_text.add(new SymbolText("F",R.drawable.f_s,R.drawable.large_f));
        list_of_text.add(new SymbolText("G",R.drawable.g_s,R.drawable.large_g));
        list_of_text.add(new SymbolText("H",R.drawable.h_s,R.drawable.large_h));
        list_of_text.add(new SymbolText("I",R.drawable.i_s,R.drawable.large_i));
        list_of_text.add(new SymbolText("J",R.drawable.j_s,R.drawable.large_j));
        list_of_text.add(new SymbolText("K",R.drawable.k_s,R.drawable.large_k));
        list_of_text.add(new SymbolText("L",R.drawable.l_s,R.drawable.large_l));
        list_of_text.add(new SymbolText("M",R.drawable.m_s,R.drawable.large_m));
        list_of_text.add(new SymbolText("N",R.drawable.n_s,R.drawable.large_n));
        list_of_text.add(new SymbolText("O",R.drawable.o_s,R.drawable.large_o));
        list_of_text.add(new SymbolText("P",R.drawable.p_s,R.drawable.large_p));
        list_of_text.add(new SymbolText("Q",R.drawable.q_s,R.drawable.large_q));
        list_of_text.add(new SymbolText("R",R.drawable.r_s,R.drawable.large_r));
        list_of_text.add(new SymbolText("S",R.drawable.s_s,R.drawable.large_s));
        list_of_text.add(new SymbolText("T",R.drawable.t_s,R.drawable.large_t));
        list_of_text.add(new SymbolText("U",R.drawable.u_s,R.drawable.large_u));
        list_of_text.add(new SymbolText("V",R.drawable.v_s,R.drawable.large_v));
        list_of_text.add(new SymbolText("W",R.drawable.w_s,R.drawable.large_w));
        list_of_text.add(new SymbolText("X",R.drawable.x_s,R.drawable.large_x));
        list_of_text.add(new SymbolText("Y",R.drawable.y_s,R.drawable.large_y));
        list_of_text.add(new SymbolText("Z",R.drawable.z_s,R.drawable.large_z));

        RecyclerView recy = (RecyclerView) findViewById(R.id.rv_id);
        _myadapter = new RecyclerViewAdapter(this,list_of_text);
        recy.setLayoutManager(new GridLayoutManager(this, 3));
        recy.setAdapter(_myadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_filter,menu);
//        getActionBar().show();
        final MenuItem menuItem = menu.findItem(R.id.search_bar);
        searchView = (SearchView) menuItem.getActionView();
        changecolor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                _myadapter.setsearchfilter(searchfilter(list_of_text, newText));
                return false;
            }
        });


        return true;
    }

    private List<SymbolText> searchfilter(List<SymbolText> mylist, String query){
        query=query.toLowerCase();
        final List<SymbolText> searchfilterlist = new ArrayList<>();
        for(SymbolText model:mylist){
            final String text = model.getTitle().toLowerCase();
            if(text.startsWith(query)){
                searchfilterlist.add(model);
            }
        }
        return searchfilterlist;
    }
    private void changecolor(View view ){
        if (view!=null){
            if(view instanceof TextView){
                ((TextView) view).setTextColor( Color.WHITE);
                return;
            }else if (view instanceof ViewGroup){
                ViewGroup viewGroup= (ViewGroup) view;
                for (int i =0; i<viewGroup.getChildCount(); i++){
                    changecolor(viewGroup.getChildAt(i));
                }
            }
        }
    }
    public void onBackPressed()
    {
        Intent myIntent = getIntent();
        String previousActivity= myIntent.getStringExtra("key");
        if (previousActivity.equals("MainTestActivity")) {
            myIntent = new Intent( this, MainTestActivity.class );
            startActivity( myIntent );
            finish();
        }
        else
        {
            myIntent = new Intent( this, MainTestActivity.class );
            startActivity( myIntent );
            finish();
        }

    }


}
