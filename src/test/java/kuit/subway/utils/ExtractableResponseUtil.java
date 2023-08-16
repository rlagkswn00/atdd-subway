package kuit.subway.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

public class ExtractableResponseUtil {
    public static ExtractableResponse<Response> post(String path, Object body) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON) // 추가 해야 합니다!
                .body(body)
                .when().post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> get(String path) {
        return RestAssured.given().log().all()
                .when().get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> delete(String path) {
        return RestAssured.given().log().all()
                .when().delete(path)
                .then().log().all()
                .extract();
    }
}
