package com.example.caikaiyang.test;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.caikaiyang.test.view.MySelectView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements MySelectView.ISelecterView {


    private MySelectView mySelectView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        List<String> title=new ArrayList<>();
        title.add("全部");
        title.add("大陆");
        title.add("港澳台");
        title.add("美国");
        title.add("日本");
        title.add("韩国");
        title.add("法国");
        title.add("英国");
        title.add("澳大利亚");

        mySelectView= (MySelectView) findViewById(R.id.hehehe);
        mySelectView.setISelecterView(this);
        mySelectView.setTitles(title);


    }


    @Override
    public void itemClick(int position) {
        mySelectView.changeBackground(position);
        Toast.makeText(this,"nidianle"+position, Toast.LENGTH_LONG).show();
    }
}
