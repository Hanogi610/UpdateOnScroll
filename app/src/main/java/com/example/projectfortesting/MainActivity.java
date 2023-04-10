package com.example.projectfortesting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenCV,RecyclerviewAdapter.ItemClickListener {

    ListView lsChap;
    ArrayList khamPhaTruyenArrayList;
    RecyclerviewAdapter adapter;
    RecyclerView gdvDSTruyen;
    NestedScrollView nestedScrollView;
    ProgressBar loading;
    String url = "https://novelfull.top/hot-novel";
    int page = 1,lastP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this,url).execute();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    if (page < lastP) {
                        page++;
                        updateContent();
                    }else {
                        loading.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }
    public class getLastPage extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                String lastPage = doc.select("li.last").select("a").attr("data-page");
                lastP = Integer.parseInt(lastPage)+1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
    private void updateContent(){
        String currentPage= url + "?page="+Integer.toString(page);
        new ApiLayTruyen(this,currentPage).execute();
        loading.setVisibility(View.VISIBLE);
    }
    private void init(){
        khamPhaTruyenArrayList = new ArrayList<>();
        new getLastPage().execute();
    }
    private void anhXa(){
        loading = findViewById(R.id.lstLoading);
        nestedScrollView = findViewById(R.id.main);
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
    }
    private void setUp(){
        int col = 4;
        khamPhaTruyenArrayList = ApiLayTruyen.KhamPhaTruyenArrayList;
        gdvDSTruyen.setLayoutManager(new GridLayoutManager(this,col));
        adapter = new RecyclerviewAdapter(this,khamPhaTruyenArrayList);
        adapter.setClickListener(this);
        gdvDSTruyen.setAdapter(adapter);
        loading.setVisibility(View.INVISIBLE);
    }
    private void setClick(){}

    @Override
    public void batDauCV() {
        Log.d("starting","okk");
    }

    @Override
    public void ketThucCV(ArrayList data) {
        khamPhaTruyenArrayList = ApiLayTruyen.KhamPhaTruyenArrayList;
        adapter = new RecyclerviewAdapter(this,khamPhaTruyenArrayList);
        gdvDSTruyen.setAdapter(adapter);
    }

    @Override
    public void biLoiCV() {
        Toast.makeText(this,"Lỗi Kết Nối",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
