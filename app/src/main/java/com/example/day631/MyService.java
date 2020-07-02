package com.example.day631;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.day631.bean.PrsBean;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyService extends Service {
    public String downloadUrl = "https://alissl.ucdl.pp.uc.cn/fs08/2019/07/05/1/110_17e4089aa3a4b819b08069681a9de74b.apk";

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                max();
            }
        }).start();


    }


    private void max() {
        final File file = new File("/storage/emulated/0/a.apk");//接收下载的文件的信息的文件
        if (!file.exists()) {//文件不存在则创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                String s = e.getMessage();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                InputStream in = response.body().byteStream();//得到下载文件的输入流（数据流）
                long max = response.body().contentLength();//得到文件的大小
                saveFile(file, max, in);
            }
        });
    }
        private void saveFile(File file, long max, InputStream in) {
            EventBus.getDefault().post(new PrsBean(1, 0, (int) max,0));
            try {
                FileOutputStream out = new FileOutputStream(file);
                int count = 0;//下载的进度
                int len = 0;//此次读取的长度
                byte[] buff = new byte[1024];
                while ((len = in.read(buff)) != -1) {//循环读取下载的文件信息
                    out.write(buff, 0, len);
                    count += len;
                    //更新进度条,打印进度
                    int n = (int) (((float)count/max)*100);
                    Log.i("111", "saveFile: "+n+","+count);
                    EventBus.getDefault().post(new PrsBean(2,count,(int)max,n));
                }
                out.close();
                in.close();
                Log.i("111", "saveFile: 下载完成");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

