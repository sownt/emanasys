package com.vosxvo.java.services.thread;

import java.util.HashMap;

public class ThreadHandle {
    private HashMap<String ,Thread> threads;
    private static final ThreadHandle INSTANCE = new ThreadHandle();

    private ThreadHandle() {
        threads = new HashMap<>();
    }

    public static ThreadHandle getInstance() {
        return ThreadHandle.INSTANCE;
    }

    public void create(String title, Runnable runnable) {
        threads.put(title, new Thread(runnable));
    }

    public void run(String title) {
        Thread thread = threads.get(title);
        thread.run();
    }

    public void run(String title, Runnable runnable) {
        Thread thread = new Thread(runnable);
        threads.put(title, thread);
        thread.run();
    }
}
