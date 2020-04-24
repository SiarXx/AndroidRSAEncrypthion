package com.example.dostepandroid.tools;

import android.os.Handler;
import android.os.HandlerThread;

public class DBWorker extends HandlerThread {
    private Handler mWorkerHandler;

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }
    public DBWorker(String name) {
        super(name);
    }
}
