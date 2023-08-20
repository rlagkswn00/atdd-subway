package kuit.subway.study.step;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.dto.SaveStationReq;

import static kuit.subway.utils.ExtractableResponseUtil.*;

public class StationStep {

    private static final String PATH = "/stations";

    public static ExtractableResponse<Response> 지하철_역_생성(SaveStationReq saveStationReq) {
        return post(PATH, saveStationReq);
    }

    public static ExtractableResponse<Response> 지하철_역_조회() {
        return get(PATH);
    }

    public static ExtractableResponse<Response> 지하철_역_삭제(Long id) {
        return delete(PATH + "/" + id);
    }
}
