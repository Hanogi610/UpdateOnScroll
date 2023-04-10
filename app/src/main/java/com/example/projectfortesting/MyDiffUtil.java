package com.example.projectfortesting;


import java.util.ArrayList;

public class MyDiffUtil extends androidx.recyclerview.widget.DiffUtil.Callback{
    private ArrayList<TruyenKhamPha> oldList;
    private ArrayList<TruyenKhamPha> newList;

    public MyDiffUtil(ArrayList<TruyenKhamPha> newArr, ArrayList<TruyenKhamPha> arr) {
        this.oldList = arr;
        this.newList = newArr;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTenTruyen()==newList.get(newItemPosition).getTenTruyen();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if(oldList.get(oldItemPosition).getTenTruyen()!=newList.get(newItemPosition).getTenTruyen()){
            return false;
        } else if (oldList.get(oldItemPosition).getLinkAnh()!=newList.get(newItemPosition).getLinkAnh()) {
            return false;
        } else if (oldList.get(oldItemPosition).getDetailUrl()!=newList.get(newItemPosition).getDetailUrl()) {
            return false;
        }
        return true;
    }
}
