package com.ugeez.exception.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UGJsonResponse {
    boolean ok;
    int code;
    String message;
    Object data;
    long timestamp;

    public static UGJsonResponse error(UGException e) {
        UGJsonResponse response = new UGJsonResponse();
        response.setOk(false);
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        response.setTimestamp(e.getTimestamp());
        return response;
    }

    public static UGJsonResponse globalError(int statusCode, String message) {
        UGJsonResponse response = new UGJsonResponse();
        response.setOk(false);
        response.setCode(statusCode);
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    public static UGJsonResponse success() {
        UGJsonResponse response = new UGJsonResponse();
        response.setOk(true);
        response.setCode(200);
        return response;
    }

    public static UGJsonResponse success(Object data) {
        UGJsonResponse response = new UGJsonResponse();
        response.setOk(true);
        response.setCode(200);
        response.setData(data);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
