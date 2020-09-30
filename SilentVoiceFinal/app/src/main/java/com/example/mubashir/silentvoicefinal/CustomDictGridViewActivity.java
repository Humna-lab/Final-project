package com.example.mubashir.silentvoicefinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by mubas on 09/04/2018.
 */

public class CustomDictGridViewActivity extends BaseAdapter
{

    private Context mContext;

    private final int[] gridViewImageId;

    public CustomDictGridViewActivity(Context context, int[] gridViewImageId) {
        mContext = context;
        this.gridViewImageId = gridViewImageId;

    }

    @Override
    public int getCount() {
        return gridViewImageId.length;
    }

    @Override
    public Object getItem(int i) {
        return gridViewImageId;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.dict_grid, null);

            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
            imageViewAndroid.setImageResource(gridViewImageId[i]);
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }


}
