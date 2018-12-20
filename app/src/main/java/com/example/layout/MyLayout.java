package com.example.layout;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.example.my_shopping.R;


public class MyLayout extends LinearLayout{


    public MyLayout(Context context) {
        super(context);
        initView();
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        View view = View.inflate(getContext(), R.layout.itme_layout,this);

    }

    }

