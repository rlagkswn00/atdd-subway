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
import java.util.Map;

import static kuit.subway.study.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.utils.ExtractableResponseUtil.*;

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



}