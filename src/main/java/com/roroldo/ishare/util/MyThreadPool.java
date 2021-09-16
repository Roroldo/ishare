package com.roroldo.ishare.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 执行邮件发送任务
 * @author 落霞不孤
 */
public class MyThreadPool {

    private static ExecutorService executors = Executors.newFixedThreadPool(2);

    public static void execute(Runnable run) {
        executors.submit(run);
    }
}
