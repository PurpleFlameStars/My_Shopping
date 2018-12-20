package com.example.my_shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.NewAdapter;
import com.example.adapter.NormalAdapter;
import com.example.bean.FoodBean;
import com.example.bean.Result;
import com.example.core.BaseCore;
import com.example.presenter.FoodPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BaseCore ,NewAdapter.FoodsCallback {

    @BindView(R.id.recycler_title)
    RecyclerView recyclerTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.text_sum)
    TextView textSum;
    @BindView(R.id.but_sum)
    Button butSum;
    private FoodPresenter presenter;
    private NewAdapter adapter1;
    private List<FoodBean> foodBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new FoodPresenter(this);
        presenter.readData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager_new = new LinearLayoutManager(this);
        linearLayoutManager_new.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager_new);
        recyclerTitle.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.but_sum)
    public void onViewClicked() {
    }

    @Override
    public void loadSuccess(Object data) {
        foodBean = (List<FoodBean>) data;
        final NormalAdapter adapter = new NormalAdapter();
        adapter.setmDatas(foodBean);
        adapter.setContext(this);
        recyclerTitle.setAdapter(adapter);
        adapter1 = new NewAdapter(MainActivity.this);
        List<FoodBean.SpusBean> spus = foodBean.get(0).getSpus();
        adapter1.setmDatas(spus);
        adapter1.setContext(MainActivity.this);
        recycler.setAdapter(adapter1);
        adapter.setOnItmeClickLister(new NormalAdapter.ItmeClickLister() {
            @Override
            public void itmeClick(View v, int position) {
                List<FoodBean.SpusBean> spus = foodBean.get(position).getSpus();
                adapter1.setmDatas(spus);
                adapter1.setContext(MainActivity.this);
                recycler.setAdapter(adapter1);
            }
        });
    }

    @Override
    public void loadError(Result result) {
        Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unBaseCore();
    }

    @Override
    public void reduceGood() {
        getSum();

    }

    @Override
    public void addAction() {
        getSum();
    }
    public void getSum(){
        double sum=0;
        int num=0;
        for (int i = 0; i < foodBean.size(); i++) {
            for (int j = 0; j < foodBean.get(i).getSpus().size(); j++) {
                sum+=foodBean.get(i).getSpus().get(j).get__v()*foodBean.get(i).getSpus().get(j).getSkus().get(0).getPrice();
                num+=foodBean.get(i).getSpus().get(j).get__v();
            }
        }
        textSum.setText("合计：¥"+sum);
        butSum.setText("去结算（"+num+"）");
    }

}
