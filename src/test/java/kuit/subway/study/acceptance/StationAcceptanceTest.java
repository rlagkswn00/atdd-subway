package kuit.subway.study.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.Map;

public class StationAcceptanceTest extends AcceptanceTest {

    @DisplayName("역 생성 테스트")
    @Test
    void createStationTest(){
        Map<String, String> body = new HashMap<>();
        body.put("name","별내역");

        ExtractableResponse<Response> extract =
                RestAssured.given().log().all()
                .contentType(ContentType.JSON) // 추가 해야 합니다!
                .body(body)
                .when().post("/station")
                .then().log().all()
                .extract();
        //200이면 정상 추가
        Assertions.assertEquals(200,extract.statusCode());
    }
}