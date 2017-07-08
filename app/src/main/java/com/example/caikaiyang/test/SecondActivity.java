package com.example.caikaiyang.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.caikaiyang.test.adapter.FirstAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements FirstAdapter.IClick {

    private RecyclerView first;
    private RecyclerView second;

    private List<String> pinDaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        first= (RecyclerView) findViewById(R.id.first);
        second= (RecyclerView) findViewById(R.id.second);

        initPinDao();
        initFirst();
    }

    private void initFirst() {
        FirstAdapter adapter=new FirstAdapter(this);
        adapter.setList(pinDaoList);
        adapter.setClick(this);
        first.setLayoutManager(new LinearLayoutManager(this));
        first.setAdapter(adapter);
    }

    private void initPinDao() {
        pinDaoList=new ArrayList<>();
        pinDaoList.add("CIBN");
        pinDaoList.add("央视频道");
        pinDaoList.add("卫视频道");
        pinDaoList.add("特色频道");
        pinDaoList.add("电台频道");
        pinDaoList.add("合作媒体");

    }


    @Override
    public void textclick(int position) {

        //根据点击不同的数据item进行请求 更新第二个recyclerview中的数据


    }
}
