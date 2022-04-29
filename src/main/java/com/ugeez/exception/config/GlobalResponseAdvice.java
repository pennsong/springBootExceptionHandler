package com.ugeez.exception.config;

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
                response.setStatusCode(HttpStatus.valueOf(
                        ((UGJsonResponse) body).getCode()
                ));
                return body;
            } else {
                response.setStatusCode(HttpStatus.OK);
                return UGJsonResponse.success(body);
            }
        }
        return body;
    }
}