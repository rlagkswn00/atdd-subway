package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.dto.SaveStationReq;

import static kuit.subway.utils.ExtractableResponseUtil.*;

public class StationStep {
    public static ExtractableResponse<Response> 지하철_역_생성(SaveStationReq saveStationReq){
        return post("/stations", saveStationReq);
    }

    public static  ExtractableResponse<Response> 지하철_역_조회(){
        return get("/stations");
    }

    public static  ExtractableResponse<Response> 지하철_역_삭제(Long id){
        return delete("/stations/" + id);
    }
}
