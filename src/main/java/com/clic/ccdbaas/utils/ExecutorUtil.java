package com.clic.ccdbaas.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorUtil {
    private static class CreateServerExecutor {
        private static final ThreadPoolExecutor cacheExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }

    public static ThreadPoolExecutor getServerExecutor() {
        return CreateServerExecutor.cacheExecutor;
    }
}
