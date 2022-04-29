package com.ugeez.exception;

import com.ugeez.exception.util.UGException;
import com.ugeez.exception.util.UGExceptionType;
import com.ugeez.exception.util.UGJsonResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @RequestMapping(value = "test/{v}")
    public UGJsonResponse test(@PathVariable String v) {
        switch (v) {
            case "SYSTEM_ERROR":
                throw new UGException(UGExceptionType.SYSTEM_ERROR);
            case "BUSINESS_ERROR":
                throw new UGException(UGExceptionType.BUSINESS_ERROR);
            case "INPUT_ERROR":
                throw new UGException(UGExceptionType.INPUT_ERROR);
            case "OTHER_ERROR":
                throw new RuntimeException("other error test");
            default:

        }
        return UGJsonResponse.success("OK");
    }
}
