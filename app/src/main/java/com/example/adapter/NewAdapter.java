package com.example.adapter;
 
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
 
import com.bumptech.glide.Glide;
import com.example.bean.FoodBean;
import com.example.layout.MyLayout;
import com.example.my_shopping.R;

import java.util.List;
 
// ① 创建Adapter
public class NewAdapter extends RecyclerView.Adapter<NewAdapter.VH>{
    private final FoodsCallback foodsCallback;
 
    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        public final MyLayout my_layout;
 
        public VH(View v) {
            super(v);
            my_layout =  v.findViewById(R.id.my_layout);
        }
    }
    
    private List<FoodBean.SpusBean> mDatas;
    private Context context;
    public Context getContext() {
        return context;
    }
 
    public void setContext(Context context) {
        this.context = context;
    }
    public List<FoodBean.SpusBean> getmDatas() {
        return mDatas;
    }
 
    public void setmDatas(List<FoodBean.SpusBean> mDatas) {
        this.mDatas = mDatas;
    }
 
    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, final int position) {
 
        TextView text_title= holder.my_layout.findViewById(R.id.text_title);
        TextView text_price= holder.my_layout.findViewById(R.id.text_price);
        ImageView image_itme= holder.my_layout.findViewById(R.id.image_itme);
        Button but_jian = holder.my_layout.findViewById(R.id.but_jian);
        Button but_jia= holder.my_layout.findViewById(R.id.but_jia);
        EditText edit_count= holder.my_layout.findViewById(R.id.edit_count);
        edit_count.setText(String.valueOf(mDatas.get(position).get__v()));
        text_title.setText(mDatas.get(position).getName());
 
        text_price.setText("¥"+mDatas.get(position).getSkus().get(0).getPrice());
        /*String replace = mDatas.get(position).getImages().replace("https", "http");
        //Log.e("===================",replace+"");
        //分割字符串
        String[] split = replace.split("!");
        //Log.e("===================++",split[0]);*/
        Glide.with(context).load(mDatas.get(position).getPic_url()).into(image_itme);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });
        but_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mDatas.get(position).set__v(mDatas.get(position).get__v()+1);
                foodsCallback.addAction();
              notifyDataSetChanged();
            }
        });
        but_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( 0 == mDatas.get(position).get__v()) return;
                mDatas.get(position).set__v(mDatas.get(position).get__v()-1);
                foodsCallback.reduceGood();
                notifyDataSetChanged();
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_layout, parent, false);
        return new VH(v);
    }
    public interface FoodsCallback{
        void reduceGood();
        void addAction();
    }
    public NewAdapter(FoodsCallback foodsCallback) {
       this.foodsCallback= foodsCallback;
    }
}
