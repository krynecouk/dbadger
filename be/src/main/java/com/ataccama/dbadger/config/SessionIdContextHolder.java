package com.ataccama.dbadger.config;

public class SessionIdContextHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(final String sessionId) {
        THREAD_LOCAL.set(sessionId);
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
