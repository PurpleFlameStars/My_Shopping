package com.example.core;
 
import com.example.bean.Result;
 
public interface BaseCore<T> {
    void loadSuccess(T data);
    void loadError(Result result);
}
