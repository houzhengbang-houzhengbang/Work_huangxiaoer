package com.example.asus.huangxiaoer.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.bean.SubscribeBean;
import com.example.asus.huangxiaoer.model.util.FrescoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.huangxiaoer.R.drawable.love;

public class Subscribe_Recy_list_Adapter  extends RecyclerView.Adapter{
    private Context context;
    private boolean pd;

    public Subscribe_Recy_list_Adapter(Context context) {
        this.context = context;
    }

    private List<SubscribeBean.DataBean> list = new ArrayList<>();

    public void setData(List<SubscribeBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.subscribe_recy_item, parent, false);
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
        FrescoUtil.setJianJin(s, viewHolder.subscribe_recy_item_img);
        viewHolder.subscribe_recy_item_name.setText(list.get(position).getName());
        viewHolder.subscribe_recy_item_juli.setText(list.get(position).getDistance());
        viewHolder.subscribe_recy_item_renjun.setText(list.get(position).getAverage_price_tip()+"元");
        viewHolder.subscribe_recy_item_pingfen.setText("评分"+list.get(position).getPack_score()+"");
        viewHolder.subscribe_recy_item_yueshou.setText(list.get(position).getMonth_sales_tip());

       // FrescoUtil.setJianJin(String.valueOf(R.drawable.nolove), viewHolder.subscribe_recy_item_love_btn);

       // FrescoUtil.setTu(String.valueOf(R.drawable.nolove),viewHolder.subscribe_recy_item_love_btn);
     //   pd = true;
/*        viewHolder.subscribe_recy_item_love_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(pd = true){
                   FrescoUtil.setJianJin(String.valueOf(R.drawable.love), viewHolder.subscribe_recy_item_love_btn);
                   pd =false;
               }
               else{
                   FrescoUtil.setJianJin(String.valueOf(R.drawable.nolove), viewHolder.subscribe_recy_item_love_btn);
                   pd =true;
               }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView subscribe_recy_item_img;
        private TextView subscribe_recy_item_name;
        private TextView subscribe_recy_item_juli;
        private ImageButton subscribe_recy_item_love_btn;
        private TextView subscribe_recy_item_pingfen;
        private TextView subscribe_recy_item_renjun;
        private TextView subscribe_recy_item_yueshou;

        public MyViewHolder(View itemView) {
            super(itemView);
            subscribe_recy_item_img = itemView.findViewById(R.id.subscribe_recy_item_img);

            subscribe_recy_item_name = itemView.findViewById(R.id.subscribe_recy_item_name);
            subscribe_recy_item_juli = itemView.findViewById(R.id.subscribe_recy_item_juli);
            subscribe_recy_item_love_btn = itemView.findViewById(R.id.subscribe_recy_item_love_btn);
            subscribe_recy_item_pingfen = itemView.findViewById(R.id.subscribe_recy_item_pingfen);
            subscribe_recy_item_renjun = itemView.findViewById(R.id.subscribe_recy_item_renjun);
            subscribe_recy_item_yueshou = itemView.findViewById(R.id.subscribe_recy_item_yueshou);

        }
    }
}
