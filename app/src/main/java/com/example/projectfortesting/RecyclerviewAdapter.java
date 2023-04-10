package com.example.projectfortesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.RecyclerViewHolder> {
    private ArrayList<TruyenKhamPha> arr;
    private Context context;
    private ItemClickListener itemClickListener;
    final int VIEW_TYPE_LOADING = 0;
    final int VIEW_TYPE_ITEM = 1;

    public RecyclerviewAdapter(Context context,ArrayList<TruyenKhamPha> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_truyen_kham_pha,parent,false);
            return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tenTruyen.setText(arr.get(position).getTenTruyen());
        String urlAnh = arr.get(position).getLinkAnh();
        Glide.with(this.context).load(urlAnh).into(holder.anhTruyen);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    @Override
    public int getItemViewType(int position) {
        return arr.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tenTruyen;
        ImageView anhTruyen;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tenTruyen = itemView.findViewById(R.id.txvTenTruyenKhamPha);
            anhTruyen = itemView.findViewById(R.id.imgAnhTruyenKhamPha);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener!=null) itemClickListener.onItemClick(v,getBindingAdapterPosition());
        }
    }
    void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public class LoadingHolder extends RecyclerView.ViewHolder{

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

