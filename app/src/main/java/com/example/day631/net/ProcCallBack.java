package com.example.day631.net;

public interface ProcCallBack<T> {
    void onSusess(T t);
    void onFain(String str);
}
