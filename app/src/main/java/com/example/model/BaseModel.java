package com.example.model;
 
import android.util.Log;

import com.example.bean.FoodBean;
import com.example.bean.Result;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
 
import java.lang.reflect.Type;
import java.util.List;
 
public class BaseModel {
    private static final String TAG = "BaseModel========";
    public static Result LoadData() {
        String url = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        String json = httpUtils.get(url);
        Log.i(TAG, "LoadData: "+json);
        Gson gson = new Gson();
        Type type = new TypeToken<Result<List<FoodBean>>>() {}.getType();
        Result result = gson.fromJson(json, type);
        return result;
    }
}
