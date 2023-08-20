package kuit.subway.study.step;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.dto.SaveLineReq;

import static kuit.subway.utils.ExtractableResponseUtil.get;
import static kuit.subway.utils.ExtractableResponseUtil.post;

public class LineStep {
    private final static String PATH = "/lines";
    public static ExtractableResponse<Response> 지하철_라인_생성(SaveLineReq saveLineReq){
        return post(PATH, saveLineReq);
    }

    public static ExtractableResponse<Response> 지하철_라인_조희(Long lineId){
        return get(PATH + "/" + lineId);
    }
}
