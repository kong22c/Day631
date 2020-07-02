package com.example.day6302.net;

public interface ProcCallBack<T> {
    void onSucess(T t);
    void onFain(String str);
}
