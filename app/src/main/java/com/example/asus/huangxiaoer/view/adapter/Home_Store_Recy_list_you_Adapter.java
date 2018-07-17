package com.example.asus.huangxiaoer.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.Home_ReXiaoBean;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class Home_Store_Recy_list_you_Adapter extends RecyclerView.Adapter{
    private Context context;
    private boolean pd;

    public Home_Store_Recy_list_you_Adapter(Context context) {
        this.context = context;
    }

    private List<Home_ReXiaoBean.DataBean.SpusBean> list = new ArrayList<>();

    public void setData(List<Home_ReXiaoBean.DataBean.SpusBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_store_recy_you_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        String images = list.get(position).getPic_url();
        String s = images;
        int i = images.indexOf("|");
        if (i != -1) {
            s = images.substring(0, i);
        }
        FrescoUtil.setJianJin(s, viewHolder.home_store_you_item_img);

        viewHolder.home_store_you_item_name.setText(list.get(position).getName());
        viewHolder.home_store_you_item_yuesou.setText("月售 "+list.get(position).getMonth_saled()+"");
        viewHolder.home_store_you_item_pirce.setText(list.get(position).getPraise_content());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView home_store_you_item_img;
        private TextView home_store_you_item_name;
        private TextView home_store_you_item_yuesou;
        private TextView home_store_you_item_pirce;


        public MyViewHolder(View itemView) {
            super(itemView);

            home_store_you_item_img = itemView.findViewById(R.id.home_store_you_item_img);
            home_store_you_item_name = itemView.findViewById(R.id.home_store_you_item_name);
            home_store_you_item_yuesou = itemView.findViewById(R.id.home_store_you_item_yuesou);
            home_store_you_item_pirce = itemView.findViewById(R.id.home_store_you_item_pirce);

        }
    }
}
