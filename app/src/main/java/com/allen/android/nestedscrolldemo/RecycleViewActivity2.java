package com.allen.android.nestedscrolldemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liyong on 2020/8/28.
 */
public class RecycleViewActivity2 extends Activity {
    //    声明一个RecycleView变量
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycle_activity2);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

//        设置RecycleView的布局方式，这里是线性布局，默认垂直
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        把适配器添加到RecycleView中
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            //        在适配器当中自定义内部类，其中的子对象用于呈现数据

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                TextView tv = new TextView(RecycleViewActivity2.this);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(16);
                tv.setTextColor(0xffffffff);
                tv.setLayoutParams(new ViewGroup.LayoutParams(-1, 100));
                return new RecyclerView.ViewHolder(tv) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
                TextView tv = (TextView) holder.itemView;
                tv.setText("item" + i);
            }


            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }

}
