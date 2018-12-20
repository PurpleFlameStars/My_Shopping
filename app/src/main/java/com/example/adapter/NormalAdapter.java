package com.example.adapter;
 
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 
import com.bumptech.glide.Glide;
import com.example.bean.FoodBean;
import com.example.my_shopping.R;

import java.util.List;
 
// ① 创建Adapter
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{
    private ItmeClickLister itmeClickLister;

    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_title);
        }
    }
    
    private List<FoodBean> mDatas;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<FoodBean> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<FoodBean> mDatas) {
        this.mDatas = mDatas;
    }

    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.title.setText(mDatas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
                itmeClickLister.itmeClick(v,position);
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
 
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_layout, parent, false);
        return new VH(v);
    }
    public interface ItmeClickLister{
        void itmeClick(View v,int position);
    }
    public void setOnItmeClickLister(ItmeClickLister itmeClickLister){
        this.itmeClickLister=itmeClickLister;
    }
}
