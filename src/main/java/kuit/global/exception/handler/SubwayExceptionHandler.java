package kuit.global.exception.handler;

import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class SubwayExceptionHandler {

    @ExceptionHandler(SubwayException.class)
    public ResponseEntity<Object> handleSubwayException(SubwayException e) {
        log.info("핸들러 진입 완료"); // 왜 못하니 왜!!
        BaseResponseStatus status = e.getStatus();
        return ResponseEntity.status(status.getHttpStatus())
                .body(status.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.info("핸들러 진입 완료"); // 왜 못하니 왜!!
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }
}
