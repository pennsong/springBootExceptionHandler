package com.ugeez.exception.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class UGException extends RuntimeException {
    int code;
    String message;
    long timestamp;

    public UGException(UGExceptionType ugExceptionType) {
        this.code = ugExceptionType.getCode();
        this.message = ugExceptionType.getDesc();
        this.timestamp = System.currentTimeMillis();
    }

    public UGException(UGExceptionType ugExceptionType, long timestamp) {
        this.code = ugExceptionType.getCode();
        this.message = ugExceptionType.getDesc();
        this.timestamp = timestamp;
    }

    public UGException(UGExceptionType ugExceptionType, String message) {
        this.code = ugExceptionType.getCode();
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
}