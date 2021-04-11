package com.maskedgeek.androidinterviewprep.threads;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args){
        AtomicBoolean processing = new AtomicBoolean(true);

        new Executor.Builder()
                .add(() -> {
            System.out.println("Task 1 Start");
            try{
                Thread.sleep(1000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("Task 1 Complete");
        }).add(() -> {
            System.out.println("Task 1 Start");
            try{
                Thread.sleep(1000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("Task 1 Complete");
        }).add(() -> {
            System.out.println("Task 1 Start");
            try{
                Thread.sleep(1000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("Task 1 Complete");
        }).callback(() -> {
                    processing.set(false);
                })
                .build()
                .execute();

        while(processing.get()){
            // KEEP MAIN THREAD ALIVE
        }

        System.out.println(" Task Terminated");

    }
}


// Next Sequence of Videos
// https://blog.mindorks.com/java-android-multithreaded-programming-runnable-callable-future-executor
