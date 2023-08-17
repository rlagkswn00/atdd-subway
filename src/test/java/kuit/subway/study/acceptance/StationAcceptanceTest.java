package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static kuit.subway.study.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.acceptance.StationStep.*;
import static kuit.subway.utils.ExtractableResponseUtil.*;
import static org.assertj.core.api.Assertions.*;

public class StationAcceptanceTest extends AcceptanceTest {


    @DisplayName("역 생성 테스트")
    @Test
    void 지하철_역_생성_테스트() {
        ExtractableResponse<Response> extract = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));
        //200이면 정상 추가
        Assertions.assertEquals(200, extract.statusCode());
    }

    @DisplayName("역 중복 생성 예외 테스트")
    @Test
    void 지하철_역_중복_생성_예외_테스트() {
        //given - 별내역 추가 상태
        ExtractableResponse<Response> extract = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));
        //200이면 정상 추가
        Assertions.assertEquals(200, extract.statusCode());

        ExtractableResponse<Response> extract2 = 지하철_역_생성(지하철_역_생성_픽스처("별내역"));
        //200이면 정상 추가
        Assertions.assertEquals(400, extract2.statusCode());
    }


    @DisplayName("역 조회 테스트")
    @Test
    void 지하철_역_전체_조회_테스트() {
        //given - 2개의 역 생성
        지하철_역_생성(지하철_역_생성_픽스처("별내역"));
        지하철_역_생성(지하철_역_생성_픽스처("별내별가람역"));

        //when - 역 조회
        ExtractableResponse<Response> extract = 지하철_역_조회();
        List<Object> responseList = extract.body().jsonPath().getList(".");

        //then 정상 코드 반환, 2개의 역 조회 되어야 함.
        Assertions.assertEquals(200, extract.statusCode());
        assertThat(responseList).extracting("name").contains("별내역", "별내별가람역");
    }

    @DisplayName("역 없을 시 조회 예외 테스트")
    @Test
    void 지하철_역_조회_예외_테스트() {
        //given - 역이 생성되지 않음

        //when - 역 조회 시도
        ExtractableResponse<Response> extract = 지하철_역_조회();

        //then HTTP Status Code 400 반환
        Assertions.assertEquals(400, extract.statusCode());
    }


    @DisplayName("역 삭제 테스트")
    @Test
    void 지하철_역_삭제_테스트() {
        //given - 1개의 역 생성
        지하철_역_생성(지하철_역_생성_픽스처("별내역"));

        //when - 역 삭제
        ExtractableResponse<Response> extract = 지하철_역_삭제(1L);

        //then 정상 코드 반환
        Assertions.assertEquals(200, extract.statusCode());
    }

    @DisplayName("미존재 역 삭제 예외 테스트")
    @Test
    void 지하철_역_삭제_예외_테스트() {
        //given - 역 생성되지 않음

        //when - 역 삭제
        ExtractableResponse<Response> extract = 지하철_역_삭제(1L);

        //then - 400 에러 코드 발생
        Assertions.assertEquals(400, extract.statusCode());
    }
}