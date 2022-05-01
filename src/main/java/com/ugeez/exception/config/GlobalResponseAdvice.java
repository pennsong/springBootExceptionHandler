package com.ugeez.exception.config;

import com.ugeez.exception.util.UGException;
import com.ugeez.exception.util.UGExceptionType;
import com.ugeez.exception.util.UGJsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

@Slf4j
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response
    ) {
        if (selectedContentType.equalsTypeAndSubtype(
                MediaType.APPLICATION_JSON
        )) {
            if (body instanceof UGJsonResponse) {
                // 有出错的情况
                response.setStatusCode(HttpStatus.valueOf(
                        ((UGJsonResponse) body).getCode()
                ));
                return body;
            } else {
                if (body instanceof LinkedHashMap) {
                    // globalError 如 404
                    LinkedHashMap map = (LinkedHashMap) body;
                    if (map.get("status") != null) {
                        int status = (int) map.get("status");
                        response.setStatusCode(HttpStatus.valueOf(status));
                        UGJsonResponse result = UGJsonResponse.globalError(status, map.get("message").toString());
                        return result;
                    } else {
                        // TODO: log 目前还没考虑到的globalError
                        return UGJsonResponse.error(new UGException(UGExceptionType.OTHER_ERROR));
                    }
                }
                // 正常返回数据的情况
                response.setStatusCode(HttpStatus.OK);
                UGJsonResponse result = UGJsonResponse.success(body);
                return result;
            }
        }
        log.info("非json情况:" + body);
        return body;
    }
}
