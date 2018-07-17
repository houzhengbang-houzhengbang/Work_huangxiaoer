package com.example.asus.huangxiaoer.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class Home_Store_Recy_list_zuo_Adapter extends RecyclerView.Adapter {
    private Context context;
    private boolean pd;
    private OnItemClick setOnItemClick;


    public Home_Store_Recy_list_zuo_Adapter(Context context) {
        this.context = context;
    }

    public void setOnItemClick(OnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }

    private List<Home_ReXiaoBean.DataBean> list = new ArrayList<>();

    public void setData(List<Home_ReXiaoBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_store_recy_zuo_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.home_store_zuo_item_name.setText(list.get(position).getName());
        viewHolder.home_store_zuo_item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemClick.setOnCl0ick(position, list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView home_store_zuo_item_name;


        public MyViewHolder(View itemView) {
            super(itemView);

            home_store_zuo_item_name = itemView.findViewById(R.id.home_store_zuo_item_name);
        }
    }

    public interface OnItemClick {
        void setOnCl0ick(int pos, List<Home_ReXiaoBean.DataBean> list);
    }

}
