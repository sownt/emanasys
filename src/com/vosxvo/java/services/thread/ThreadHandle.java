package com.vosxvo.java.services.thread;

import java.util.HashMap;

public class ThreadHandle {
    private static HashMap<String, Thread> threads;
    private static volatile ThreadHandle INSTANCE;

    private ThreadHandle() {}

    public static ThreadHandle getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadHandle.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadHandle();
                    threads = new HashMap<>();
                }
            }
        }
        return INSTANCE;
    }

    public void create(String title, Runnable runnable) {
        threads.put(title, new Thread(runnable));
    }

    public void run(String title) {
        Thread thread = threads.get(title);
        thread.start();
    }

    public void run(String title, Runnable runnable) {
        Thread thread = new Thread(runnable);
        threads.put(title, thread);
        thread.start();
    }
}
