package com.example.caikaiyang.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caikaiyang.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caikaiyang on 2017/7/8.
 */

public class MySelectView extends LinearLayout {


//    private TextView textView;
//    private LinearLayout parent;

    //用来存放显示的名称的
    private List<String> taglist=new ArrayList<>();

    private List<TextView> textViews;

    private int marginleft;
    private int marginright;
    private int margintop;
    private int marginbottom;

    private int paddingleft;
    private int paddingright;
    private int paddingtop;
    private int paddingbottom;

    private float textsize;
    private int textcolor;

    private Context context;

    private ISelecterView iSelecterView;

    public MySelectView(Context context) {
        this(context, null);
    }

    public MySelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySelectView);
        if (typedArray != null) {
            for (int i = 0; i < typedArray.length(); i++) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.MySelectView_textSize:
                        //设置textview的字体大小
                        textsize = typedArray.getDimension(R.styleable.MySelectView_textSize, 14);
                        break;
                    case R.styleable.MySelectView_textColor:
                        //设置所有textview的字体颜色  默认为黑色
                        textcolor = typedArray.getColor(R.styleable.MySelectView_textColor, Color.BLACK);
                        break;
                    case R.styleable.MySelectView_textPadding_top:
                        //设置textview的paddingtop
                        paddingtop=(int) typedArray.getDimension(R.styleable.MySelectView_textPadding_top, 0);
                        break;
                    case R.styleable.MySelectView_textPadding_bottom:

                        paddingbottom=(int) typedArray.getDimension(R.styleable.MySelectView_textPadding_bottom, 0);
                        break;
                    case R.styleable.MySelectView_textPadding_left:
                        paddingleft=(int) typedArray.getDimension(R.styleable.MySelectView_textPadding_left, 0);
                        break;
                    case R.styleable.MySelectView_textPadding_right:
                        paddingright=(int) typedArray.getDimension(R.styleable.MySelectView_textPadding_right, 0);

                        break;
                    case R.styleable.MySelectView_textMargin_top:
                        margintop = (int) typedArray.getDimension(R.styleable.MySelectView_textMargin_top, 0);
                        break;
                    case R.styleable.MySelectView_textMargin_bottom:
                        marginbottom = (int) typedArray.getDimension(R.styleable.MySelectView_textMargin_bottom, 0);
                        break;

                    case R.styleable.MySelectView_textMargin_left:
                        marginleft = (int) typedArray.getDimension(R.styleable.MySelectView_textMargin_left, 0);
                        break;

                    case R.styleable.MySelectView_textMargin_right:
                        marginright = (int) typedArray.getDimension(R.styleable.MySelectView_textMargin_right, 0);
                        break;


                }
            }


        }


    }

    public MySelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MySelectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {

        textViews = new ArrayList<>();
        for (int i = 0; i < taglist.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(taglist.get(i));
            textViews.add(textView);
            addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    iSelecterView.itemClick(finalI);
                }
            });
        }
        //设置第一个默认选中
        changeBackground(0);


    }
    public void setTitles(List<String> list){
        this.taglist=list;
    }
    public void setISelecterView(ISelecterView iSelecterView){
        this.iSelecterView=iSelecterView;
    }



    private boolean isone=true;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(isone){
            init(context);
            isone=false;
            Log.i("cky","111");
        }
        int measuredWidth = 0;
        int measuredHeight = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (TextView view : textViews) {
            layoutParams.setMargins(marginleft, margintop, marginright, marginbottom);
            view.setLayoutParams(layoutParams);

            view.setPadding(paddingleft,paddingtop,paddingright,paddingbottom);
            view.setTextColor(textcolor);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        }

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);

        //wrapcontent
        if (widthSpaceMode == MeasureSpec.AT_MOST && heightSpaceMode == MeasureSpec.AT_MOST) {

            for (int i = 0; i < getChildCount(); i++) {
                measuredWidth += getChildAt(i).getMeasuredWidth() + marginleft + marginright;
            }
            measuredHeight = getChildAt(0).getMeasuredHeight() + margintop + marginbottom;
            setMeasuredDimension(measuredWidth, measuredHeight);
        }



    }

    /**
     * 改变背景
     * @param position
     */
    public void changeBackground(int position){
        textViews.get(position).setBackgroundResource(R.drawable.bg_myselectview);
        cleanOtherBackground(position);
    }

    public void cleanOtherBackground(int position){
        for(int i=0;i<textViews.size();i++){
            if(i==position){
                continue;
            }
            textViews.get(i).setBackgroundResource(0);
        }
    }



    /**
     * 设置点击变色
     */
    public interface ISelecterView{
        void itemClick(int position);
    }

}
