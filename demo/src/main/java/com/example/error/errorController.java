package com.example.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.pojo.Error;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class errorController {
    private static final Logger logger = LoggerFactory.getLogger(errorController.class);




    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Error errorPage(Exception e) {
        logger.info("==========================统一异常处理机制开始运行================================");
        Error error = new Error();
        error.setCode(404);
        error.setMessage(e.getMessage());
        return error;
    }
}