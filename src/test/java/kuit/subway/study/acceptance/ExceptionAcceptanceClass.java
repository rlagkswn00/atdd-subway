package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.global.BaseResponseStatus;
import kuit.subway.AcceptanceTest;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.study.fixture.LineFixture;
import kuit.subway.study.step.LineStep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static kuit.global.BaseResponseStatus.*;
import static kuit.global.BaseResponseStatus.DUPLICATE_STATION;
import static kuit.global.BaseResponseStatus.NOT_EXIST_STATION;
import static kuit.subway.study.fixture.LineFixture.라인_수정_픽스처;
import static kuit.subway.study.fixture.LineFixture.라인_픽스처;
import static kuit.subway.study.fixture.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.step.LineStep.*;
import static kuit.subway.study.step.StationStep.*;
import static org.assertj.core.api.Assertions.assertThat;
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
        //given - 4호선 추가
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //when - 4호선 중복 추가
        지하철_역_생성(지하철_역_생성_픽스처("도봉산역"));
        지하철_역_생성(지하철_역_생성_픽스처("온수역"));


        ExtractableResponse<Response> lineDuplicateResponse = 지하철_라인_생성(라인_픽스처("khaki", 25L, "4호선", 3L, 4L));

        //then - BAD_REQUEST 400 에러 반환
        Assertions.assertEquals(DUPLICATE_STATION.getHttpStatus().value(), lineDuplicateResponse.statusCode());
    }

    @DisplayName("미존재 라인 조회 예외 테스트")
    @Test
    void 지하철_라인_조회_예외_테스트() {
        //given - 라인 미존재

        //when - ID값 1번 라인 조회
        ExtractableResponse<Response> response = 지하철_라인_조희(1L);

        //then - BAD_REQUEST 400 에러 반환
        assertThat(response.statusCode())
                .isEqualTo(NOT_EXIST_LINE.getHttpStatus().value());
    }

    @DisplayName("미존재 라인 삭제 예외 테스트")
    @Test
    void 지하철_라인_삭제_예외_테스트() {
        //given - 라인 미존재

        //when - ID값 1번 라인 삭제
        ExtractableResponse<Response> response = 지하철_라인_삭제(1L);

        //then - BAD_REQUEST 400 에러 반환
        assertThat(response.statusCode())
                .isEqualTo(NOT_EXIST_LINE.getHttpStatus().value());
    }


    @DisplayName("미존재 라인 수정 예외 테스트")
    @Test
    void 지하철_라인_수정_예외_테스트() {
        //given - 라인 미존재

        //when - 지하철 노선 수정
        ExtractableResponse<Response> response = 지하철_라인_수정(1L,
                라인_수정_픽스처("green", 33L, "4호선", 2L, 1L));

        //then - BAD REQUEST 400 에러 반환
        assertThat(response.statusCode())
                .isEqualTo(NOT_EXIST_LINE.getHttpStatus().value());

    }

    @DisplayName("라인 생성 시 미존재 역 설정 예외 테스트")
    @Test
    void 지하철_라인_미존재역_예외_테스트() {
        //given - 역 미존재

        //when - 지하철 라인 생성
        ExtractableResponse<Response> response = 지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //then - BAD_REUQEST 400 반환
        assertThat(response.statusCode())
                .isEqualTo(NOT_EXIST_STATION.getHttpStatus().value());

    }

    @DisplayName("라인 생성 시 상행역과 하행역 동일 ID 설정 예외 테스트")
    @Test
    void 지하철_라인_동일역_예외_테스트() {
        //given - 역 1개 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));

        //when - 상행역 ID와 하행역 ID 동일하게 설정하여 생성 ㅇ시도
        ExtractableResponse<Response> response = 지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 1L));

        //then - BAD_REQEUST 400 반환
        assertThat(response.statusCode())
                .isEqualTo(SAME_UP_DOWN_STATION.getHttpStatus().value());

    }

}
