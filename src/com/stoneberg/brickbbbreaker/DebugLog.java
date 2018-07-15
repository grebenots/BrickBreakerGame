package com.stoneberg.brickbbbreaker;

import java.util.Date;

public class DebugLog {

    private long timestamp;
    private String message;

    public DebugLog(String message) {
        this.message = message;
        timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
