package com.example.projectfortesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class KhamPhaTruyenAdapter extends ArrayAdapter<TruyenKhamPha> {

    private Context ct;
    private ArrayList<TruyenKhamPha> arr;

    public KhamPhaTruyenAdapter(@NonNull Context context, int resource, @NonNull List<TruyenKhamPha> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(R.layout.item_truyen_kham_pha,null);
        }
        if(arr.size()>0){
            TruyenKhamPha truyen = this.arr.get(position);
            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyenKhamPha);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnhTruyenKhamPha);

            tenTenTruyen.setText(truyen.getTenTruyen());
            Glide.with(this.ct).load(truyen.getLinkAnh()).into(imgAnhTruyen);
        }
        return convertView;
    }
}
