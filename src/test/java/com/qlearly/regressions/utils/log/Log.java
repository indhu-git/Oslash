package com.qlearly.regressions.utils.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Log {
    private static final Logger Log = LogManager.getLogger(Log.class);

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message, Object t) {
        Log.error(message);
        throw new RuntimeException(t.toString());
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
