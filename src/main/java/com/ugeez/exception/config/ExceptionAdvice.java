package com.ugeez.exception.config;

import com.ugeez.exception.util.UGException;
import com.ugeez.exception.util.UGExceptionType;
import com.ugeez.exception.util.UGJsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(UGException.class)
    @ResponseBody
    public UGJsonResponse ugException(UGException e) {
        if (e.getCode() == UGExceptionType.SYSTEM_ERROR.getCode()) {
            // TODO: 保存e到LOG
        }
        log.info(e.toString());
        return UGJsonResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public UGJsonResponse exception(Exception exception) {
        long timestamps = System.currentTimeMillis();
        // TODO: 保存exception(带timestamps)到LOG
        UGException e = new UGException(UGExceptionType.OTHER_ERROR, timestamps);
        return UGJsonResponse.error(e);
    }
}
