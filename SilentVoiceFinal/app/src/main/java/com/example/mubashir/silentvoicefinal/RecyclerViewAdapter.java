package com.example.mubashir.silentvoicefinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mubas on 09/04/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<SymbolText> mylist;

    public RecyclerViewAdapter(Context context, List<SymbolText> mylist) {
        this.context = context;
        this.mylist = mylist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.symbols_text,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_symbol_text.setText(mylist.get(position).getTitle());
        holder.img_thumbnail.setImageResource(mylist.get(position).getThumbnail());
        holder.cv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SymbolDetail.class);
                intent.putExtra("Title",mylist.get(position).getTitle());
                intent.putExtra("Image",mylist.get(position).getImageId());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public void setsearchfilter(List<SymbolText> list_item){
        mylist = new ArrayList<>();
        mylist.addAll(list_item);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_symbol_text;
        ImageView img_thumbnail;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_symbol_text = (TextView) itemView.findViewById(R.id.text_id);
            img_thumbnail = (ImageView) itemView.findViewById(R.id.symbol_id);
            cv = (CardView)itemView.findViewById(R.id.cardview_id);

        }
    }
}
