package com.example.projectfortesting;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ApiLayTruyen extends AsyncTask<Void,Void,Void> {

    LayTruyenCV layTruyenCV;
    public static ArrayList<TruyenKhamPha> KhamPhaTruyenArrayList = new ArrayList<>();
    String url;

    public ApiLayTruyen(LayTruyenCV layTruyenCV,String url) {
        this.layTruyenCV = layTruyenCV;
        this.layTruyenCV.batDauCV();
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements data = doc.select("div.col-truyen-main").select("div.row");
            int size = data.size();
            for(int i=0;i<size;i++){
                String linkAnh = "https://novelfull.top" + data
                        .select("img")
                        .eq(i)
                        .attr("src");
                String tenTruyen = data
                        .select("h3")
                        .eq(i)
                        .text();
                String detailURL = "https://novelfull.top" + data.select("h3")
                        .eq(i)
                        .select("a")
                        .attr("href");
                KhamPhaTruyenArrayList.add(new TruyenKhamPha(tenTruyen,linkAnh,detailURL));
                Log.d("items"," img: " + linkAnh + " . title: " + tenTruyen + " . detail url: " + detailURL);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(KhamPhaTruyenArrayList==null){
            this.layTruyenCV.biLoiCV();
        }else{
            this.layTruyenCV.ketThucCV(KhamPhaTruyenArrayList);
        }
    }
}
