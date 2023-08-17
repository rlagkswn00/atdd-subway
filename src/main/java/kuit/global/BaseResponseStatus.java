package kuit.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
@RequiredArgsConstructor
public enum BaseResponseStatus {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    DUPLICATE_STATION(HttpStatus.BAD_REQUEST, "중복된 역 입니다."),
    NOT_EXIST_STATION(HttpStatus.BAD_REQUEST, "역이 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;

}
