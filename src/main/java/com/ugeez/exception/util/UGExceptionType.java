package com.ugeez.exception.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum UGExceptionType {
    SYSTEM_ERROR(500, "SYSTEM_ERROR"),
    BUSINESS_ERROR(500, "BUSINESS_ERROR"),
    INPUT_ERROR(500, "INPUT_ERROR"),
    OTHER_ERROR(500, "OTHER_ERROR");

    int code;
    String desc;

    UGExceptionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
