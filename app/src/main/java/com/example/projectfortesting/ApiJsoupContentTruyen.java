package com.example.projectfortesting;

import android.os.AsyncTask;
import android.util.Log;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ApiJsoupContentTruyen extends AsyncTask<Void,Void,Void> {
    LayTruyenCV layTruyenCV;
    ArrayList<ChapTruyen> arrChapters = new ArrayList<>();

    public ApiJsoupContentTruyen(LayTruyenCV layTruyenCV) {
        this.layTruyenCV = layTruyenCV;
        this.layTruyenCV.batDauCV();
    }



    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Document doc = Jsoup.connect("https://novelfull.top/library-of-heavens-path.html").get();
            Elements content = doc.select("ul.list-chapter").select("span.chapter-text");
            int size = content.size();
            for(int i=0;i<size;i++){
                String tenChap = content
                        .eq(i)
                        .text();
                String chapUrl =content
                        .eq(i)
                        .attr("href");
                arrChapters.add(new ChapTruyen(tenChap,chapUrl));
                Log.d("truyen chap"," .ten chap: "+tenChap+" .chapUrl: " + chapUrl);
            }

        }catch (IOException e){
            arrChapters = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(arrChapters==null){
            this.layTruyenCV.biLoiCV();
        }else {
            this.layTruyenCV.ketThucCV(arrChapters);
        }
    }
}
