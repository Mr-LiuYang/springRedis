package springRedis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by liuyang on 2017/6/24.
 */
@ControllerAdvice
public class SpitterExceptionHandler{

    @ExceptionHandler(SpitterNotFoundException.class)
    public String spitterNotFoundHandler(){
        return "error";
    }

    @ExceptionHandler(SpitterDuplicateException.class)
    public String spitterDuplicateHandler(){
        return "error1";
    }
}
