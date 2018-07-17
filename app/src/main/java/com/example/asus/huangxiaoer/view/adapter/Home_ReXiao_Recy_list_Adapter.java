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
import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class Home_ReXiao_Recy_list_Adapter extends RecyclerView.Adapter{
    private Context context;
    private boolean pd;

    public Home_ReXiao_Recy_list_Adapter(Context context) {
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
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_rexiao_recy_item, parent, false);
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
        FrescoUtil.setJianJin(s, viewHolder.home_rexiao_item_img);
        viewHolder.home_rexiao_item_name.setText(list.get(position).getName());
        viewHolder.home_rexiao_item_price.setText(list.get(position).getMonth_saled_content());

     //   FrescoUtil.setJianJin(String.valueOf(R.drawable.homeadd), viewHolder.home_rexiao_item_add);
      //  FrescoUtil.setTu(String.valueOf(R.drawable.homeadd),viewHolder.home_rexiao_item_add);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView home_rexiao_item_img;
        private TextView home_rexiao_item_name;
        private TextView home_rexiao_item_price;
        private ImageButton home_rexiao_item_add;


        public MyViewHolder(View itemView) {
            super(itemView);

            home_rexiao_item_img = itemView.findViewById(R.id.home_rexiao_item_img);
            home_rexiao_item_name = itemView.findViewById(R.id.home_rexiao_item_name);
            home_rexiao_item_price = itemView.findViewById(R.id.home_rexiao_item_price);
            home_rexiao_item_add = itemView.findViewById(R.id.home_rexiao_item_add);


        }
    }
}
