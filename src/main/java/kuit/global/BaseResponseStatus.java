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
    NOT_EXIST_STATION(HttpStatus.BAD_REQUEST, "역이 존재하지 않습니다."),

    DUPLICATE_LINE(HttpStatus.BAD_REQUEST, "중복된 라인 입니다."),
    NOT_EXIST_LINE(HttpStatus.BAD_REQUEST, "라인이 존재하지 않습니다."),
    SAME_UP_DOWN_STATION(HttpStatus.BAD_REQUEST, "상행역과 하행역의 ID가 동일합니다."),
    INVALID_UPSTATION_SECTION(HttpStatus.BAD_REQUEST, "라인의 하행역과 새로운 구간의 상행역이 같지 않습니다."),
    ONLY_ONE_SECTION(HttpStatus.BAD_REQUEST,"라인에 구간이 하나뿐이라 삭제 할 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;

}
