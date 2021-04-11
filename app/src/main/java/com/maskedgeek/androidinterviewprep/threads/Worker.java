package com.maskedgeek.androidinterviewprep.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker implements Runnable{

    private Thread thread;
    private Runnable runnable;
    private AtomicBoolean started;
    private CountDownLatch latch;

    public Worker(Runnable task, CountDownLatch latch){
        thread = new Thread(this);
        started = new AtomicBoolean(false);
        runnable = task;
        this.latch = latch;
    }

    public void start(){
        if(!started.getAndSet(true)){
            thread.start();
        }
    }

    @Override
    public void run(){
        runnable.run();
    }
}
