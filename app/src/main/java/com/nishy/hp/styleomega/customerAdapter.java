package com.nishy.hp.styleomega;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class customerAdapter  extends BaseAdapter {
    Context context;
    ArrayList<customers> arrayList;

    public customerAdapter(Context context, ArrayList<customers> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
