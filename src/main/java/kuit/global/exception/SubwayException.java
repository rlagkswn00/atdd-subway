package kuit.global.exception;

import kuit.global.BaseResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SubwayException extends RuntimeException{
    private final BaseResponseStatus status;
}
