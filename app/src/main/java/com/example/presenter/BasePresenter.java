package com.example.presenter;
 
import android.os.AsyncTask;
 
import com.example.bean.Result;
import com.example.core.BaseCore;
 
public abstract class BasePresenter {
    private  BaseCore baseCore;
 
    public BasePresenter(BaseCore baseCore) {
        this.baseCore = baseCore;
    }
    public void unBaseCore(){
        baseCore = null;
    }
    public void readData(Object... strings){
        new MyTask().execute(strings);
    }
    class MyTask extends AsyncTask<Object,Void,Result>{
 
        @Override
        protected Result doInBackground(Object... strings) {
            Result result = onModel(strings);
            return result;
        }
 
        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            if (result.getStatus()==200){
                baseCore.loadSuccess(result.getData());
            }else {
                baseCore.loadError(result);
            }
 
        }
    }
    public abstract Result onModel(Object... strings);
}
