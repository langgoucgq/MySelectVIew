package com.example.caikaiyang.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caikaiyang.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caikaiyang on 2017/7/8.
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstHolder> {

    private Context context;
    private List<String> list;

    private IClick click;

    //缓存 用于存放被点击过后的textview
    private List<TextView> textViews;


    public FirstAdapter(Context context){
        this.context=context;
        list=new ArrayList<>();
        textViews=new ArrayList<>();
    }

    public void setList(List<String> list){
        this.list=list;
    }
    public void setClick(IClick iClick){
        this.click=iClick;
    }

    @Override
    public FirstAdapter.FirstHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        return new FirstHolder(view);
    }

    @Override
    public void onBindViewHolder(FirstHolder holder, final int position) {

        String name = list.get(position);
        holder.textView.setText(name);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view=((TextView)v);
                if (textViews.isEmpty()){
                    textViews.add(view);
                    view.setTextColor(Color.BLUE);
                }else{
                    TextView beforeView = textViews.remove(0);
                    beforeView.setTextColor(Color.BLACK);
                    textViews.add(view);
                    view.setTextColor(Color.BLUE);
                }

                click.textclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class FirstHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public FirstHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_pindao_name);
        }
    }


    public interface IClick{
        void textclick(int position);
    }
}
