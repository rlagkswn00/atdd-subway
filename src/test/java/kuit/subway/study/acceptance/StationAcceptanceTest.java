package kuit.subway.study.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.study.StationFixture;
import kuit.subway.utils.ExtractableResponseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kuit.subway.study.StationFixture.지하철_역_생성_픽스처;
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

    private ExtractableResponse<Response> 지하철_역_생성(SaveStationReq saveStationReq){
        return post("/station", saveStationReq);
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
        assertThat(responseList).extracting("name").contains("별내역","별내별가람역");
    }

    private ExtractableResponse<Response> 지하철_역_조회(){
        return get("/stations");
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

    private ExtractableResponse<Response> 지하철_역_삭제(Long id){
        return delete("/station/" + id);
    }



}