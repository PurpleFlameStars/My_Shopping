package com.example.presenter;

import com.example.bean.Result;
import com.example.core.BaseCore;
import com.example.model.BaseModel;

public class FoodPresenter extends BasePresenter {
    public FoodPresenter(BaseCore baseCore) {
        super(baseCore);
    }

    @Override
    public Result onModel(Object... strings) {
        return BaseModel.LoadData();
    }
}
