package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.study.fixture.LineFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kuit.global.BaseResponseStatus.DUPLICATE_STATION;
import static kuit.global.BaseResponseStatus.NOT_EXIST_STATION;
import static kuit.subway.study.fixture.LineFixture.라인_픽스처;
import static kuit.subway.study.fixture.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.step.LineStep.지하철_라인_생성;
import static kuit.subway.study.step.StationStep.*;
import static org.springframework.http.HttpStatus.CREATED;

public class ExceptionAcceptanceClass extends AcceptanceTest {

    @DisplayName("역 중복 생성 예외 테스트")
    @Test
    void 지하철_역_중복_생성_예외_테스트() {
        //given - 별내역 추가 상태
        ExtractableResponse<Response> stationResponse = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));
        //200이면 정상 추가
        Assertions.assertEquals(CREATED.value(), stationResponse.statusCode());

        //when - 똑같은 이름의 "별내역" 추가
        ExtractableResponse<Response> stationDuplicateResponse = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));

        //then - BAD_REQUEST 400 에러 반환
        Assertions.assertEquals(DUPLICATE_STATION.getHttpStatus().value(), stationDuplicateResponse.statusCode());
    }

    @DisplayName("역 없을 시 조회 예외 테스트")
    @Test
    void 지하철_역_조회_예외_테스트() {
        //given - 역이 생성되지 않음

        //when - 역 조회 시도
        ExtractableResponse<Response> extract = 지하철_역_조회();

        //then - HTTP Status Code 400 반환
        Assertions.assertEquals(NOT_EXIST_STATION.getHttpStatus().value(), extract.statusCode());
    }

    @DisplayName("미존재 역 삭제 예외 테스트")
    @Test
    void 지하철_역_삭제_예외_테스트() {
        //given - 역 생성되지 않음

        //when - 역 삭제
        ExtractableResponse<Response> extract = 지하철_역_삭제(1L);

        //then - 400 에러 코드 발생
        Assertions.assertEquals(NOT_EXIST_STATION.getHttpStatus().value(), extract.statusCode());
    }

    @DisplayName("라인 중복 생성 예외 테스트")
    @Test
    void 지하철_라인_중복_생성_예외_테스트() {
        //given - 별내역 추가 상태
        ExtractableResponse<Response> stationResponse = 지하철_라인_생성(라인_픽스처("green",22L,"4호선",1L,2L));
        //200이면 정상 추가
        Assertions.assertEquals(CREATED.value(), stationResponse.statusCode());

        //when - 똑같은 이름의 "별내역" 추가
        ExtractableResponse<Response> stationDuplicateResponse = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));

        //then - BAD_REQUEST 400 에러 반환
        Assertions.assertEquals(DUPLICATE_STATION.getHttpStatus().value(), stationDuplicateResponse.statusCode());
    }
}
